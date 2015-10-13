package org.elsysbg.ip.java;

public class ClassExample {

	public static void main(String[] args) {
		Room room = new Room();
		
		room.setHeight(5);
		room.setWidth(7);
		
		final int height = room.getHeight();
//		final int width = room.getWidth();
		System.out.println(height);
//		System.out.println(width);
		
		System.out.println(room.getWidth());
		System.out.println(room.calculateArea());
	}

}
