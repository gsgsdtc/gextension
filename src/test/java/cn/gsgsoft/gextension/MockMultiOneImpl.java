package cn.gsgsoft.gextension;

import java.util.Collection;

import cn.gsgsoft.gextension.annotation.SPIParam;

public class MockMultiOneImpl implements MockMutiImplInterface{
	
	private Collection<MockMutiImplInterface> mockMultiImplList;
	private MockMutiImplInterface tow;

	public Collection<MockMutiImplInterface> getMockMultiImplList() {
		return mockMultiImplList;
	}
	
	@SPIParam(spiType=MockMutiImplInterface.class)
	public void setMockMultiImplList(Collection<MockMutiImplInterface> mockMultiImplList) {
		this.mockMultiImplList = mockMultiImplList;
	}
	
	public MockMutiImplInterface getTow() {
		return tow;
	}
	
	@SPIParam(defaultIpml=false)
	public void setTow(MockMutiImplInterface tow) {
		this.tow = tow;
	}
	
	
	
}
