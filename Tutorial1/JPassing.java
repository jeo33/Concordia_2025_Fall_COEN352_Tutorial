class MyObject {
	int value;

	MyObject(int value) {
    	this.value = value;
	}
}

public class JPassing {
	public static void modifyPrimitive(int x) {
    	System.out.println("Inside modifyPrimitive: x's value is " + x);
    	x = 100; // Only changes the copy of the value
	}

	public static void modifyObjectState(MyObject obj) {
    	System.out.println("Inside modifyObjectState: hash code of obj is " + System.identityHashCode(obj));
    	obj.value = 200; // Changes the state of the original object
	}

	public static void reassignObject(MyObject obj) {
    	System.out.println("Inside reassignObject: hash code of obj is " + System.identityHashCode(obj));
    	obj = new MyObject(300); // Reassigns the local reference, not the original
    	System.out.println("Inside reassignObject after reassignment: hash code of obj is now " + System.identityHashCode(obj));
	}

	public static void main(String[] args) {
    	int myNum = 10;
    	System.out.println("Original primitive value: " + myNum);
    	modifyPrimitive(myNum);
    	System.out.println("After modifyPrimitive: " + myNum);

    	MyObject myObj = new MyObject(10);
    	System.out.println("\nOriginal object hash code: " + System.identityHashCode(myObj));
    	System.out.println("Original object value: " + myObj.value);
    	modifyObjectState(myObj);
    	System.out.println("After modifyObjectState: " + myObj.value);

    	MyObject anotherObj = new MyObject(10);
    	System.out.println("\nOriginal object hash code before reassign: " + System.identityHashCode(anotherObj));
    	System.out.println("Original object value before reassign: " + anotherObj.value);
    	reassignObject(anotherObj);
    	System.out.println("After reassignObject: " + anotherObj.value+" "+System.identityHashCode(anotherObj));
	}
}
