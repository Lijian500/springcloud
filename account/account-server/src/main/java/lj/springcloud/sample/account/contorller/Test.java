package lj.springcloud.sample.account.contorller;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author lijian
 * @date 2021-09-17 15:29
 */
public class Test {


	public static void main (String args[]) {
		int[] arr = {6, 1, 9, 3, 7};
		Class c = arr.getClass();
		if (c.isArray()) {
			Class arrayType = arr.getClass().getComponentType();
			System.out.println("The array is of type: " + arrayType);
			System.out.println("The length of the array is: " + Array.getLength(arr));
			System.out.print("The array elements are: ");
			for(int i: arr) {
				System.out.print(i + " ");
			}
		}
	}
}
