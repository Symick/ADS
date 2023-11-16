package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.Student;
import struct.Heap;
import struct.PriorityQueue;


public class HeapTestLauncher {

	public HeapTestLauncher() {
		super();
	}
	
	public static void main(String[] args) {
		HeapTestLauncher me = new HeapTestLauncher();
		me.launch();
	}
	
	private void launch() {
	/*	Testcode om een lijst met namen om te zetten in een heap
	 *  en een tweede lijst toe te voegen.
	 *  Uncomment om te gebruiken.
	*/	
		String[] namenLijst = {"Jan", "Kees", "Gerke", "Annie", "Jane", "Mo", "Frank", "Steven", "Eva", "Tanja"};
		String[] tweedeLijst = {"Zarina", "Sophie", "Klaas", "Karel", "Abel", "Bert", "Vera", "Jantina", "Sinan", "Wendy", "Guus", "Titus", "Coco"};
		Heap<String> woordenHeap = new Heap<>();
		woordenHeap.makeHeap(namenLijst);
		woordenHeap.addArrayOfItems(tweedeLijst);
		System.out.println(woordenHeap);
		woordenHeap.removeItem();
		System.out.println(woordenHeap);
		woordenHeap.addItem("Annet");
		System.out.println(woordenHeap);
		String[] namen = {"Julian", "Brian"};
		woordenHeap.addArrayOfItems(namen);
		System.out.println(woordenHeap);

/*		Testcode om een lijst met student te genereren en die toe te voegen aan een Priority Queue
*/		
		int aantalStudenten = 10;
		Student[] studentList = Student.generateStudents(aantalStudenten);
		PriorityQueue<Student> studentPQ = new PriorityQueue<>();
		studentPQ.addArrayToPQ(studentList);
		System.out.println(Arrays.toString(studentList));
		System.out.println(studentPQ);
		// Maak een lijst van studenten op basis van prioriteit
		ArrayList<Student> priorityList = new ArrayList<>();
		for (int i = 0; i < aantalStudenten; i++) {
			priorityList.add((Student) studentPQ.dequeue());
		}
 		System.out.println(priorityList);
	}
	
	

}
