package cn.gsgsoft.gextension.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;



/**
 * annotation相关工具类
 * @author guosg
 *
 */
public class AnnotationUtils {
	private static final Map<Class<?>, Boolean> annotatedInterfaceCache = new WeakHashMap<Class<?>, Boolean>();
	/**
	 * Get a single {@link Annotation} of {@code annotationType} from the supplied
	 * Method, Constructor or Field. Meta-annotations will be searched if the annotation
	 * is not declared locally on the supplied element.
	 * @param ae the Method, Constructor or Field from which to get the annotation
	 * @param annotationType the annotation class to look for, both locally and as a meta-annotation
	 * @return the matching annotation or {@code null} if not found
	 * @since 3.1
	 */
	public static <T extends Annotation> T getAnnotation(AnnotatedElement ae, Class<T> annotationType) {
		T ann = ae.getAnnotation(annotationType);
		if (ann == null) {
			for (Annotation metaAnn : ae.getAnnotations()) {
				ann = metaAnn.annotationType().getAnnotation(annotationType);
				if (ann != null) {
					break;
				}
			}
		}
		return ann;
	}
	
	/**
	 * Get a single {@link Annotation} of {@code annotationType} from the supplied {@link Method},
	 * traversing its super methods if no annotation can be found on the given method itself.
	 * <p>Annotations on methods are not inherited by default, so we need to handle this explicitly.
	 * @param method the method to look for annotations on
	 * @param annotationType the annotation class to look for
	 * @return the annotation found, or {@code null} if none found
	 */
	public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationType) {
		A annotation = getAnnotation(method, annotationType);
		Class<?> clazz = method.getDeclaringClass();
		if (annotation == null) {
			annotation = searchOnInterfaces(method, annotationType, clazz.getInterfaces());
		}
		while (annotation == null) {
			clazz = clazz.getSuperclass();
			if (clazz == null || clazz.equals(Object.class)) {
				break;
			}
			try {
				Method equivalentMethod = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
				annotation = getAnnotation(equivalentMethod, annotationType);
			}
			catch (NoSuchMethodException ex) {
				// No equivalent method found
			}
			if (annotation == null) {
				annotation = searchOnInterfaces(method, annotationType, clazz.getInterfaces());
			}
		}
		return annotation;
	}
	
	private static <A extends Annotation> A searchOnInterfaces(Method method, Class<A> annotationType, Class<?>[] ifcs) {
		A annotation = null;
		for (Class<?> iface : ifcs) {
			if (isInterfaceWithAnnotatedMethods(iface)) {
				try {
					Method equivalentMethod = iface.getMethod(method.getName(), method.getParameterTypes());
					annotation = getAnnotation(equivalentMethod, annotationType);
				}
				catch (NoSuchMethodException ex) {
					// Skip this interface - it doesn't have the method...
				}
				if (annotation != null) {
					break;
				}
			}
		}
		return annotation;
	}
	
	private static boolean isInterfaceWithAnnotatedMethods(Class<?> iface) {
		synchronized (annotatedInterfaceCache) {
			Boolean flag = annotatedInterfaceCache.get(iface);
			if (flag != null) {
				return flag;
			}
			boolean found = false;
			for (Method ifcMethod : iface.getMethods()) {
				if (ifcMethod.getAnnotations().length > 0) {
					found = true;
					break;
				}
			}
			annotatedInterfaceCache.put(iface, found);
			return found;
		}
	}
	
	/**
	 * Find a single {@link Annotation} of {@code annotationType} from the supplied {@link Class},
	 * traversing its interfaces and superclasses if no annotation can be found on the given class itself.
	 * <p>This method explicitly handles class-level annotations which are not declared as
	 * {@link java.lang.annotation.Inherited inherited} <i>as well as annotations on interfaces</i>.
	 * <p>The algorithm operates as follows: Searches for an annotation on the given class and returns
	 * it if found. Else searches all interfaces that the given class declares, returning the annotation
	 * from the first matching candidate, if any. Else proceeds with introspection of the superclass
	 * of the given class, checking the superclass itself; if no annotation found there, proceeds
	 * with the interfaces that the superclass declares. Recursing up through the entire superclass
	 * hierarchy if no match is found.
	 * @param clazz the class to look for annotations on
	 * @param annotationType the annotation class to look for
	 * @return the annotation found, or {@code null} if none found
	 */
	public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {
		A annotation = clazz.getAnnotation(annotationType);
		if (annotation != null) {
			return annotation;
		}
		for (Class<?> ifc : clazz.getInterfaces()) {
			annotation = findAnnotation(ifc, annotationType);
			if (annotation != null) {
				return annotation;
			}
		}
		if (!Annotation.class.isAssignableFrom(clazz)) {
			for (Annotation ann : clazz.getAnnotations()) {
				annotation = findAnnotation(ann.annotationType(), annotationType);
				if (annotation != null) {
					return annotation;
				}
			}
		}
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null || superClass.equals(Object.class)) {
			return null;
		}
		return findAnnotation(superClass, annotationType);
	}
	
	
	/**
	 * Determine whether an annotation for the specified {@code annotationType} is
	 * declared locally on the supplied {@code clazz}. The supplied {@link Class}
	 * may represent any type.
	 * <p>Note: This method does <strong>not</strong> determine if the annotation is
	 * {@linkplain java.lang.annotation.Inherited inherited}. For greater clarity
	 * regarding inherited annotations, consider using
	 * {@link #isAnnotationInherited(Class, Class)} instead.
	 * @param annotationType the Class object corresponding to the annotation type
	 * @param clazz the Class object corresponding to the class on which to check for the annotation
	 * @return {@code true} if an annotation for the specified {@code annotationType}
	 * is declared locally on the supplied {@code clazz}
	 * @see Class#getDeclaredAnnotations()
	 * @see #isAnnotationInherited(Class, Class)
	 */
	public static boolean isAnnotationDeclaredLocally(Class<? extends Annotation> annotationType, Class<?> clazz) {
		boolean declaredLocally = false;
		for (Annotation annotation : clazz.getDeclaredAnnotations()) {
			if (annotation.annotationType().equals(annotationType)) {
				declaredLocally = true;
				break;
			}
		}
		return declaredLocally;
	}
	
	/**
	 * Find the first {@link Class} in the inheritance hierarchy of the specified {@code clazz}
	 * (including the specified {@code clazz} itself) which declares an annotation for the
	 * specified {@code annotationType}, or {@code null} if not found. If the supplied
	 * {@code clazz} is {@code null}, {@code null} will be returned.
	 * <p>If the supplied {@code clazz} is an interface, only the interface itself will be checked;
	 * the inheritance hierarchy for interfaces will not be traversed.
	 * <p>The standard {@link Class} API does not provide a mechanism for determining which class
	 * in an inheritance hierarchy actually declares an {@link Annotation}, so we need to handle
	 * this explicitly.
	 * @param annotationType the Class object corresponding to the annotation type
	 * @param clazz the Class object corresponding to the class on which to check for the annotation,
	 * or {@code null}
	 * @return the first {@link Class} in the inheritance hierarchy of the specified {@code clazz}
	 * which declares an annotation for the specified {@code annotationType}, or {@code null}
	 * if not found
	 * @see Class#isAnnotationPresent(Class)
	 * @see Class#getDeclaredAnnotations()
	 * @see #findAnnotationDeclaringClassForTypes(List, Class)
	 * @see #isAnnotationDeclaredLocally(Class, Class)
	 */
	public static Class<?> findAnnotationDeclaringClass(Class<? extends Annotation> annotationType, Class<?> clazz) {
		if (clazz == null || clazz.equals(Object.class)) {
			return null;
		}
		
		for(Class<?> i : clazz.getInterfaces()){
			if(isAnnotationDeclaredLocally(annotationType,i)){
				return i;
			}
		}
		return (isAnnotationDeclaredLocally(annotationType, clazz)) ? clazz : findAnnotationDeclaringClass(
			annotationType, clazz.getSuperclass());
	}

}
