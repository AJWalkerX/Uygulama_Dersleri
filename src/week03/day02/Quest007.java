package week03.day02;
/*
7- Dizide 13 sayısına denk gelene kadar devam edelim. 13 sayısı bulunduktan sonra ondan bir sonraki gelen sayı 13 değil ise 13'den sonra gelen sayıyı toplama ekleyelim.
-> 1, 13, 13, 13, 5, 13
 */
public class Quest007 {
	public static void main(String[] args) {
		int[] nums = {1, 13, 13, 13, 5, 13};
		int sum = 0;
		
		for(int i = 0; i < nums.length-1; i++){
			if (nums[i] == 13){
				if (nums[(i+1)] != 13){
					sum += nums[(i+1)];
				}
			}
		}
		System.out.println(sum);
	}
}