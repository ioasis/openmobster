/**
 * Copyright (c) {2003,2009} {openmobster@gmail.com} {individual contributors as indicated by the @authors tag}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openmobster.core.mobileCloud.manager.gui.screens;

import java.util.Vector;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.system.Display;

import org.openmobster.core.mobileCloud.manager.gui.LocaleKeys;
import org.openmobster.core.mobileCloud.api.ui.framework.Services;
import org.openmobster.core.mobileCloud.api.ui.framework.SystemLocaleKeys;
import org.openmobster.core.mobileCloud.api.ui.framework.command.CommandContext;
import org.openmobster.core.mobileCloud.api.ui.framework.navigation.Screen;
import org.openmobster.core.mobileCloud.api.ui.framework.resources.AppResources;



/**
 * @author openmobster@gmail.com
 */
public class CometConfigScreen extends Screen
{		
	private MainScreen screen;
	private ListField listField;
	
	public CometConfigScreen()
	{
		
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	public Object getContentPane() 
	{		
		//return this.controlPanel;
		return this.screen;
	}
	
	public void render()
	{								
		AppResources appResources = Services.getInstance().getResources();					
		this.screen = new MainScreen();
		this.screen.setTitle(appResources.localize(LocaleKeys.push_settings, LocaleKeys.push_settings));
												
		listField = new ListField(2);
		listField.setCallback(new ListFieldCallbackImpl());		
				
		this.screen.add(listField);
		this.setMenuItems();
	}
	
	private void setMenuItems()
	{		
		AppResources resources = Services.getInstance().getResources();
		
		MenuItem selectItem = new MenuItem(resources.localize(LocaleKeys.select, LocaleKeys.select), 1, 1){
			public void run()
			{
				//UserInteraction/Event Processing...this is where the Commands can be executed
				CometConfigScreen.this.handle();
			}
		};
		
		MenuItem backItem = new MenuItem(resources.localize(SystemLocaleKeys.back, SystemLocaleKeys.back), 2, 2){
			public void run()
			{
				//Go Home
				Services.getInstance().getNavigationContext().home();
			}
		};
								 										
		this.screen.addMenuItem(selectItem);
		this.screen.addMenuItem(backItem);
	}	
	
	private void handle()
	{
		int selectedIndex = this.listField.getSelectedIndex();
		
		CommandContext commandContext;
		switch(selectedIndex)
		{
			case 0:	
				//Handle setting push mode
				commandContext = new CommandContext();
				commandContext.setTarget("cometConfig");
				commandContext.setAttribute("mode", "push");				
				Services.getInstance().getCommandService().execute(commandContext);
			break;
			
			case 1:
				//Handle setting poll mode
				commandContext = new CommandContext();
				commandContext.setTarget("cometConfig");
				commandContext.setAttribute("mode", "poll");
				Services.getInstance().getCommandService().execute(commandContext);
			break;
						
			
			default:
				//Do nothing					
			break;
		}
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	private static class ListFieldCallbackImpl implements ListFieldCallback
	{
		private Vector actions = new Vector();
		
		private ListFieldCallbackImpl()
		{			
			AppResources resources = Services.getInstance().getResources();
			this.actions.addElement(resources.localize(LocaleKeys.realtime_push_mode, LocaleKeys.realtime_push_mode));
			this.actions.addElement(resources.localize(LocaleKeys.poll_mode, LocaleKeys.poll_mode));			
		}

		public void drawListRow(ListField listField, Graphics graphics, int index,
		int y, int width) 
		{
			String action = (String)this.actions.elementAt(index);									
			graphics.drawText(action, 0, y);
		}
		
		public Object get(ListField listField, int index) 
		{			
			return this.actions.elementAt(index);
		}

		public int getPreferredWidth(ListField listField) 
		{			
			return Display.getWidth();
		}

		public int indexOfList(ListField listField, String prefix, int start) 
		{			
			return this.actions.indexOf(prefix, start);
		}		
	}		
}