package com.TOH2;

public class TOH2 {
	public static void main(String[]args) {
		TOH2 ob = new TOH2();
		int diks_num=3;
		Stack src, dest, aux;
		src=ob.createStack(diks_num,'S');
		dest=ob.createStack(diks_num,'D');
		aux=ob.createStack(diks_num,'A');
		
		ob.tohIterative(src, dest, aux, diks_num);
	}
	
	class Stack {
		int capacity;
		int top;
		int[] array;
		char present;
	}
	Stack createStack(int capacity, char present) {
		Stack s = new Stack();
		s.capacity=capacity;
		s.present=present;
		s.top=-1;
		s.array= new int[capacity];
		return s;
	}
	void push(Stack s,int item) {
		if(s.top==s.capacity-1) {
			return;
		}
		s.array[++s.top]=item;
	}
	int pop(Stack s) {
		if(s.top==-1) {
			return -1;
		}
		return s.array[s.top--];
	}
	void moveDisk(Stack src, Stack dest, int disk) {
		System.out.println("Move disk "+disk+" from "+src.present+" to "+dest.present);

	}
	void moveDiskBetweenTwoPoles(Stack src, Stack dest) {

		int srcTopDisk=pop(src);
		int destTopDisk=pop(dest);
		
		if(srcTopDisk==-1) {
			push(src,destTopDisk);
			moveDisk(src, dest, destTopDisk);
		}
		else if (destTopDisk==-1) {
			push(dest,srcTopDisk);
			moveDisk(dest, src, srcTopDisk);
		}
		else if (srcTopDisk>destTopDisk) {
			push(src,srcTopDisk);
			push(src,destTopDisk);
			moveDisk(dest, src, destTopDisk);
		}
		else {
			push(dest,destTopDisk);
			push(dest,srcTopDisk);
			moveDisk(src, dest, srcTopDisk);
		}
		
	
	}
	void tohIterative(Stack src, Stack dest, Stack aux, int disks_num) {
		int moves_num = (int) Math.pow(2,disks_num)-1;
		int i;
		if(disks_num%2==0) {
			Stack temp=dest;
			dest=aux;
			aux=temp;
		}
		for(i=disks_num;i>=1;i--) {
			push(src,i);
		}
		for(i=1;i<=moves_num;i++) {
			if(i%3==1) {
				moveDiskBetweenTwoPoles(src,dest);
			}
			else if (i%3==2) {
				moveDiskBetweenTwoPoles(src,aux);

			}
			else if (i%3==0) {
				moveDiskBetweenTwoPoles(aux,dest);

			}
		}
	}
}
//Comprehended