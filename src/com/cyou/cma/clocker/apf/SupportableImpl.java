package com.cyou.cma.clocker.apf;

public class SupportableImpl implements Supportable {

	@Override
	public boolean isSupportThemeSetting(int clockerVersion) {
		return clockerVersion>=21;	
	}

    @Override
    public boolean isSupportNewFrameWork() {
        return true;
    }

	@Override
	public boolean isSupportRecommendSlide() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSupportShortcut() {
		// TODO Auto-generated method stub
		return false;
	}

}
