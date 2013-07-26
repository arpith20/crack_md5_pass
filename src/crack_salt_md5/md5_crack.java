package crack_salt_md5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;

public class md5_crack {
	public static void main(String args[]) throws Exception {

		// read password files; contains all possible passwords
		FileInputStream fstream1 = new FileInputStream("passwords.txt");
		DataInputStream in1 = new DataInputStream(fstream1);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

		// 
		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" 0-salt+pepper+pass\n 1-salt+pass\n\tEnter your choice: ");
		choice = Integer.parseInt(br.readLine());
		
		BufferedReader br2;
		if (choice == 1) {
			System.out.println("Choice: Salt+Password");
			FileReader fr2 = new FileReader("u_name3.txt");
			br2 = new BufferedReader(fr2);
		} else {
			System.out.println("Choice: Salt+Pepper+Password");
			FileReader fr2 = new FileReader("u_name4.txt");
			br2 = new BufferedReader(fr2);
		}

		String line;
		String pass;

		String[] hero = new String[100];
		String[] ar = new String[100];
		String[] corr = new String[100];
		String[] temp = new String[4];

		int i = 0;
		while ((line = br2.readLine()) != null) {
			temp = line.split(":");
			hero[i] = temp[1];
			ar[i] = temp[2];
			corr[i] = temp[3];
			System.out.println(hero[i] + " " + ar[i] + " " + corr[i]);
			i++;
		}
		int length = i;
		System.out.println(length);
		br2.close();
		//int j = 7;
		i = 0;
		while ((pass = br1.readLine()) != null) {
			for (i = 0; i < length; i++) {
				if (choice == 0) {
					for (int j = 0; j <= 9; j++) {//pepper can take values from 0-9
						String s = ar[i] + j + pass;
						String output = "";
						MessageDigest m = MessageDigest.getInstance("MD5");
						m.update(s.getBytes(), 0, s.length());
						output = new BigInteger(1, m.digest()).toString(16);
						if (output.equalsIgnoreCase(corr[i]))
							System.out.println(hero[i] + ":" + j + ":" + pass);
					}
				} else {
					String s = ar[i] + pass;
					String output = "";
					MessageDigest m = MessageDigest.getInstance("MD5");
					m.update(s.getBytes(), 0, s.length());
					output = new BigInteger(1, m.digest()).toString(16);
					if (output.equalsIgnoreCase(corr[i]))
						System.out.println(hero[i] + ":" + pass);
				}
			}
		}
		in1.close();
		System.out.println("All possible values found!!");
	}
}
