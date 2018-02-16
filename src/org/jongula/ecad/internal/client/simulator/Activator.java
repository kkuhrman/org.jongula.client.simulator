/**
 * @package:	org.jongula.ecad.client.simulator
 * @file:		Activator.java
 * @brief:      Bundle activator for client circuit simulator.
 * 
 * @copyright:	Copyright (C) 2018 Kuhrman Technology Solutions LLC
 * @license:	GPLv3+: GNU GPL version 3
 * @authors:	Karl Kuhrman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jongula.ecad.internal.client.simulator;

import org.jongula.ecad.node.INode;
import org.jongula.ecad.signal.ISignal;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private INode node;
	private ServiceReference nodeRef;
	private ISignal signal;
	private ServiceReference signalRef;
	private CircuitMonitor monitor;
	
	/**
	 * 
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Launching");
		monitor = new CircuitMonitor();
		signalRef = context.getServiceReference(ISignal.class.getName());
		if (signalRef == null) {
			System.err.println("Failed to acquire reference to ISignal service.");
		}
		nodeRef = context.getServiceReference(INode.class.getName());
		if (nodeRef == null) {
			System.err.println("Failed to acquire reference to INode service.");
		}
		signal = (ISignal) context.getService(signalRef);
		if (signal == null) {
			System.err.println("Failed to acquire ISignal from service reference.");
		}
		node = (INode) context.getService(nodeRef);
		if (nodeRef == null) {
			System.err.println("Failed to acquire INode from service reference.");
		}
		monitor.setNode(node);
		monitor.setSignal(signal);
		monitor.startup();
	}
	
	/**
	 * 
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		monitor.shutdown();
		if (signalRef != null)
			context.ungetService(signalRef);
		if (nodeRef != null)
			context.ungetService(nodeRef);
		System.out.println("Terminating");
	}

}
