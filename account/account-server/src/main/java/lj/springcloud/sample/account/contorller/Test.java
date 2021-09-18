package lj.springcloud.sample.account.contorller;

import lj.springcloud.sample.common.domian.Result;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author lijian
 * @date 2021-09-17 15:29
 */
public class Test {


	public static void main (String args[]) {
		int[] arr = {6, 1, 9, 3, 7};
		Result<int[]> success = Result.success(arr);

		// Class c = arr.getClass();
		Class<? extends int[]> c = success.getResultData().getClass();
		if (c.isArray()) {
			Class arrayType = arr.getClass().getComponentType();
			Class arrayType2 = c.getComponentType();
			System.out.println("The array is of type: " + arrayType);
			System.out.println("The length of the array is: " + Array.getLength(success.getResultData()));
			System.out.println("-----------------------------");
			System.out.println("The array is of type: " + arrayType2);
			System.out.println("The length of the array is: " + Array.getLength(arrayType2));
		}
	}
}
