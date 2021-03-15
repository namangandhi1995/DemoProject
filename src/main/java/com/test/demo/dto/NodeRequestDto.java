package com.test.demo.dto;

import java.io.Serializable;

import com.test.demo.Entities.Node;

public class NodeRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4886719486405753459L;

	private Node parent;
	private Node child;
	private int level;
	private int edge;

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEdge() {
		return edge;
	}

	public void setEdge(int edge) {
		this.edge = edge;
	}

}
