/**
 * @package:	org.jongula.ecad.client.simulator
 * @file:		CircuitMonitor.java
 * @brief:      Defines a fake circuit monitoring device for code testing.
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
import org.jongula.ecad.node.INodeSensor;
import org.jongula.ecad.signal.ISignal;

public class CircuitMonitor implements INodeSensor {

	/**
	 * 
	 */
	private INode node;
	
	private ISignal signal;
	
	@Override
	public void signaled() {
		System.out.println("Node signaled I=" + signal.getCurrent()
				+ " V=" + signal.getPotential());
	}
	
	/**
	 * 
	 */
	public void shutdown() {
		node.removeSensor(this);
	}
	
	/**
	 * 
	 */
	public void startup() {
		node.addSensor(this);
	}

	/**
	 * @return the node
	 */
	public INode getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(INode node) {
		this.node = node;
	}

	/**
	 * @return the signal
	 */
	public ISignal getSignal() {
		return signal;
	}

	/**
	 * @param signal the signal to set
	 */
	public void setSignal(ISignal signal) {
		this.signal = signal;
	}
}
