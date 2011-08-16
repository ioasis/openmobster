/**
 * Copyright (c) {2003,2011} {openmobster@gmail.com} {individual contributors as indicated by the @authors tag}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openmobster.core.mobileCloud.api;

import org.openmobster.core.mobileCloud.api.sync.MobileBean;

/**
 * @author openmobster@gmail.com
 */
public class TestBeanReadAll extends AbstractAPITest 
{
	@Override
	public void runTest()
	{
		try
		{
			this.startBootSync();
			this.waitForBeans();
			
			MobileBean[] beans = MobileBean.readAll(this.service);
			this.assertNotNull(beans, this.getInfo()+"/MustNotBeNull");
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
	}
}