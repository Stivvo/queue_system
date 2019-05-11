package test;

import java.net.*;
import java.io.*;

public class server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ServerSocket ss = new ServerSocket(4999);
		Socket s = ss.accept();
		
	}

}
