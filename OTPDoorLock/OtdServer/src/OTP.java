
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTP{

	int OTP1;
	 int OTP2;
	public String number1 = "1234";
	public static int y=0;
	public int OTP(String a,int x) {
	    	


		   		int Pw = Integer.parseInt(a);
		      //일련번호
		      int Sn = 4581;
		      
		      int[] temp1 = new int [5];
		      int[] temp2 = new int [4];
		      int[] temp3 = new int [4];
		      
		      
		      //일련번호 + 비밀번호, OTP 계산하기
		      for(int i=0;i<5;i++)
		      {
		         temp1[i] = Pw+Sn;
		         
		         temp2[0] = (Pw/1000);
		         temp2[1] = (Pw-(temp2[0]*1000))/100;
		         temp2[2] = (Pw-(temp2[0]*1000)-(temp2[1]*100))/10;
		         temp2[3] = (Pw-(temp2[0]*1000)-(temp2[1]*100)-(temp2[2]*10));
		      
		         temp3[0] = (Sn/1000);
		         temp3[1] = ((Sn-(temp3[0]*1000))/100);
		         temp3[2] = (Sn-(temp3[0]*1000)-(temp3[1]*100))/10;
		         temp3[3] = (Sn-(temp3[0]*1000)-(temp3[1]*100)-(temp3[2]*10));
		            
		            for(int j = 0; j<temp2.length; j++)
		            {
		               //1씩 증가 
		               temp2[j] = temp2[j] + 1;
		               temp3[j] = temp3[j] + 1;
		               
		               if(temp2[j]>=10)
		                  temp2[j] = temp2[j] - 10;
		                  
		         
		               if(temp3[j]>=10)
		                  temp3[j] = temp3[j] - 10;
		               
		               
		            }
		            Pw = (temp2[0]*1000)+(temp2[1]*100)+(temp2[2]*10)+(temp2[3]);
		            Sn = (temp3[0]*1000)+(temp3[1]*100)+(temp3[2]*10)+(temp3[3]);
		            
		            
		         //   System.out.println(temp1[i]);
		            
		            
		      }
		      
		      
		   
		   
		      // 지정해놓은 배열
		      
		      int OTPArray[][] = { 
		                        {1,3,4,5,2,1,3,2,3,6,
		                        8,4,3,3,7,2,5,7,6,4,
		                        7,5,3,7,4,3,9,0,6,2,
		                        7,9,4,2,9,4,2,5,7,0,
		                        7,4,2,5,6,8,9,4,4,7,
		                        4,4,5,2,4,5,2,4,6,8,
		                        6,4,1,0,9,4,2,7,4,3,
		                        6,8,8,2,4,6,8,9,7,4,
		                        3,1,4,6,8,0,4,3,8,0,
		                        2,5,3,2,6,8,9,3,6,8},
		                        {2,4,5,6,8,3,2,7,9,0,
		                        3,2,5,6,3,3,5,6,8,8,
		                        7,5,7,8,9,7,4,4,3,4,
		                        2,5,0,8,7,9,5,3,5,6,
		                        0,7,5,2,8,6,3,2,4,8,
		                        3,5,8,0,5,3,5,8,3,2,
		                        4,7,2,5,2,8,9,4,3,7,
		                        2,4,9,2,4,3,4,5,7,8,
		                        2,0,8,7,5,5,8,9,0,7,
		                        3,9,7,4,6,8,0,1,4,8}, 
		            
		                        {1,0,2,9,4,7,3,5,7,8,
		                        2,4,3,7,2,3,4,6,1,2,
		                        1,3,5,0,2,1,2,5,6,7,
		                        7,1,5,7,2,5,9,2,5,0,
		                        3,1,5,7,4,9,1,9,4,2,
		                        2,5,2,8,3,8,3,7,9,3,
		                        0,8,7,5,2,5,6,2,5,8,
		                        7,5,4,6,2,4,6,7,8,9,
		                        6,5,3,7,0,8,3,0,4,2,
		                        2,4,5,6,9,4,2,6,8,3},
		               
		            
		                        {7,5,3,2,5,7,3,2,6,0,
		                        2,5,6,7,3,5,6,7,8,5,
		                        2,3,5,7,8,9,0,0,2,5,
		                        2,4,5,7,9,0,9,7,3,2,
		                        8,7,6,5,4,3,7,8,4,2,
		                        2,4,5,1,1,2,4,5,4,5,
		                        2,3,4,1,6,7,9,6,4,4,
		                        2,1,4,7,5,3,7,4,3,6,
		                        0,9,7,5,3,7,2,4,7,6,
		                        5,4,1,3,8,6,8,9,6,4},
		            
		                        {2,3,5,3,2,9,0,8,6,5,
		                        2,1,6,4,3,2,6,7,8,9,
		                        0,7,4,2,5,8,3,7,1,3,
		                        4,2,8,6,5,0,7,5,4,8,
		                        2,1,4,5,6,2,7,8,9,3,
		                        8,1,9,6,4,7,0,2,4,3,
		                        3,0,5,9,3,2,8,4,4,7,
		                        0,2,8,3,7,2,7,9,2,4,
		                        1,9,2,0,3,7,4,0,7,6,
		                        0,3,2,6,4,8,2,6,3,5},
		            
		                        {2,4,7,6,4,2,0,5,2,4,
		                        7,5,3,9,4,3,2,5,7,8,
		                        1,5,8,4,2,6,0,8,0,4,
		                        8,3,2,5,6,8,8,2,1,2,
		                        4,2,3,4,6,2,4,7,5,7,
		                        9,3,5,7,2,1,9,5,3,5,
		                        5,3,6,7,6,7,1,7,2,1,
		                        6,8,5,1,1,5,2,9,1,2,
		                        3,7,9,1,3,5,7,4,3,5,
		                        5,0,7,1,2,5,3,6,8,5},
		            
		                        {7,6,9,6,0,8,1,2,4,8,
		                        3,5,8,5,9,0,7,1,2,7,
		                        9,3,2,6,3,6,9,0,6,4,
		                        9,8,6,4,2,2,4,6,1,4,
		                        7,5,3,6,7,2,6,0,5,4,
		                        1,2,5,8,2,0,6,4,2,8,
		                        3,5,7,8,9,0,2,7,4,3,
		                        3,7,5,8,0,3,3,9,4,8,
		                        5,7,3,0,6,7,3,8,3,3,
		                        8,6,0,9,3,8,4,3,0,5},
		            
		                        {8,2,9,3,4,7,5,2,9,4,
		                        7,5,9,3,7,9,8,4,5,7,
		                        0,9,2,5,7,3,3,9,7,1,
		                        4,5,7,9,8,4,5,7,9,0,
		                        2,4,7,5,9,0,8,7,5,2,
		                        0,3,9,4,7,2,0,4,9,0,
		                        4,5,7,2,4,5,6,2,9,3,
		                        8,7,5,9,2,0,2,3,9,8,
		                        4,5,7,0,2,9,7,8,5,5,
		                        7,3,9,4,5,0,1,9,5,7},
		                           
		                       {9,2,3,4,5,1,0,9,8,5,
		                        0,3,2,4,5,0,9,8,1,7,
		                        9,8,5,7,2,0,3,8,5,0,
		                          5,7,4,9,2,0,9,4,5,7,
		                        2,9,4,8,5,7,2,9,4,8,
		                        5,7,2,0,9,4,8,5,7,2,
		                        9,0,4,8,5,7,2,8,5,9,
		                        7,1,9,0,5,8,1,5,8,4,
		                        7,4,9,4,5,7,9,4,5,9,
		                        0,4,8,5,7,2,9,0,5,1},
		            
		                       {9,0,3,0,9,1,0,3,9,4,
		                      0,9,8,0,7,9,9,8,7,9,
		                      2,8,3,7,5,9,0,2,8,5,
		                      4,7,8,5,6,1,8,5,3,4,
		                      9,5,1,0,9,8,5,2,9,8,
		                      6,0,2,4,9,0,1,8,7,7,
		                      0,9,1,2,6,8,7,5,8,9,
		                      1,7,9,3,8,9,7,7,9,8,
		                      9,3,4,9,0,2,3,3,4,8,
		                      4,9,0,2,8,5,4,7,8,9} };
		      //더한 값 나열하기
		        String arr, arr1, arr2;
		        long time = System.currentTimeMillis()/1000/60 - 25773120+x+y;
		        BigInteger bigtime = BigInteger.valueOf(time);
		        BigInteger bigtime1 = BigInteger.valueOf(time-1);
		        BigInteger bigtime2 = BigInteger.valueOf(time+1);
		        arr = Integer.toString(temp1[0])+ Integer.toString(temp1[1]) + Integer.toString(temp1[2]) + Integer.toString(temp1[3]) + Integer.toString(temp1[4]);
		        arr1 = Integer.toString(temp1[0])+ Integer.toString(temp1[1]) + Integer.toString(temp1[2]) + Integer.toString(temp1[3]) + Integer.toString(temp1[4]);
		        arr2 = Integer.toString(temp1[0])+ Integer.toString(temp1[1]) + Integer.toString(temp1[2]) + Integer.toString(temp1[3]) + Integer.toString(temp1[4]);
		    
		        BigInteger bignum = new BigInteger(arr);
		        BigInteger bignum1 = new BigInteger(arr1);
		        BigInteger bignum2 = new BigInteger(arr2);
		        
		        bignum = bignum.add(bigtime);
		        bignum1 = bignum1.add(bigtime1);
		        bignum2 = bignum1.add(bigtime2);
		        
		        arr = bignum.toString();
		        arr1 = bignum.toString();
		        arr2 = bignum.toString();
		        
		        //문자열 끊기
		        String aa = arr.substring(arr.length()-3, arr.length());//시작 : 끝에서 3번째 자리 , 끝 :마지막 자리
		        String bb = arr.substring(arr.length()-6, arr.length()-3);
		        String cc = arr.substring(arr.length()-9, arr.length()-6);
		        String dd = arr.substring(arr.length()-12, arr.length()-9);
		        String ee = arr.substring(arr.length()-15, arr.length()-12);
		        String ff = arr.substring(arr.length()-18, arr.length()-15);

		        String aa1 = arr1.substring(arr1.length()-3, arr1.length()); //시작 : 끝에서 3번째 자리 , 끝 :마지막 자리
		        String bb1 = arr1.substring(arr1.length()-6, arr1.length()-3);
		        String cc1 = arr1.substring(arr1.length()-9, arr1.length()-6);
		        String dd1 = arr1.substring(arr1.length()-12, arr1.length()-9);
		        String ee1 = arr1.substring(arr1.length()-15, arr1.length()-12);
		        String ff1 = arr1.substring(arr1.length()-18, arr1.length()-15);

		        String aa2 = arr2.substring(arr2.length()-3, arr2.length()); //시작 : 끝에서 3번째 자리 , 끝 :마지막 자리
		        String bb2 = arr2.substring(arr2.length()-6, arr2.length()-3);
		        String cc2 = arr2.substring(arr2.length()-9, arr2.length()-6);
		        String dd2 = arr2.substring(arr2.length()-12, arr2.length()-9);
		        String ee2 = arr2.substring(arr2.length()-15, arr2.length()-12);
		        String ff2 = arr2.substring(arr2.length()-18, arr2.length()-15);


		        // 좌표 추출
		        int aX = Integer.parseInt(aa.substring(0,1));
		        int aY = Integer.parseInt(aa.substring(1,3));
		        int bX = Integer.parseInt(bb.substring(0,1));
		        int bY = Integer.parseInt(bb.substring(1,3));
		        int cX = Integer.parseInt(cc.substring(0,1));
		        int cY = Integer.parseInt(cc.substring(1,3));
		        int dX = Integer.parseInt(dd.substring(0,1));
		        int dY = Integer.parseInt(dd.substring(1,3));
		        int eX = Integer.parseInt(ee.substring(0,1));
		        int eY = Integer.parseInt(ee.substring(1,3));
		        int fX = Integer.parseInt(ff.substring(0,1));
		        int fY = Integer.parseInt(ff.substring(1,3));

		        int aX1 = Integer.parseInt(aa1.substring(0,1));
		        int aY1 = Integer.parseInt(aa1.substring(1,3));
		        int bX1 = Integer.parseInt(bb1.substring(0,1));
		        int bY1 = Integer.parseInt(bb1.substring(1,3));
		        int cX1 = Integer.parseInt(cc1.substring(0,1));
		        int cY1 = Integer.parseInt(cc1.substring(1,3));
		        int dX1 = Integer.parseInt(dd1.substring(0,1));
		        int dY1 = Integer.parseInt(dd1.substring(1,3));
		        int eX1 = Integer.parseInt(ee1.substring(0,1));
		        int eY1 = Integer.parseInt(ee1.substring(1,3));
		        int fX1 = Integer.parseInt(ff1.substring(0,1));
		        int fY1 = Integer.parseInt(ff1.substring(1,3));

		        int aX2 = Integer.parseInt(aa2.substring(0,1));
		        int aY2 = Integer.parseInt(aa2.substring(1,3));
		        int bX2 = Integer.parseInt(bb2.substring(0,1));
		        int bY2 = Integer.parseInt(bb2.substring(1,3));
		        int cX2 = Integer.parseInt(cc2.substring(0,1));
		        int cY2 = Integer.parseInt(cc2.substring(1,3));
		        int dX2 = Integer.parseInt(dd2.substring(0,1));
		        int dY2 = Integer.parseInt(dd2.substring(1,3));
		        int eX2 = Integer.parseInt(ee2.substring(0,1));
		        int eY2 = Integer.parseInt(ee2.substring(1,3));
		        int fX2 = Integer.parseInt(ff2.substring(0,1));
		        int fY2 = Integer.parseInt(ff2.substring(1,3));

		        //좌표 대입
		        int realA = OTPArray [aX][aY];
		        int realB = OTPArray [bX][bY];
		        int realC = OTPArray [cX][cY];
		        int realD = OTPArray [dX][dY];
		        int realE = OTPArray [eX][eY];
		        int realF = OTPArray [fX][fY];

		        int realA1 = OTPArray [aX1][aY1];
		        int realB1 = OTPArray [bX1][bY1];
		        int realC1 = OTPArray [cX1][cY1];
		        int realD1 = OTPArray [dX1][dY1];
		        int realE1 = OTPArray [eX1][eY1];
		        int realF1 = OTPArray [fX1][fY1];

		        int realA2 = OTPArray [aX2][aY2];
		        int realB2 = OTPArray [bX2][bY2];
		        int realC2 = OTPArray [cX2][cY2];
		        int realD2 = OTPArray [dX2][dY2];
		        int realE2 = OTPArray [eX2][eY2];
		        int realF2 = OTPArray [fX2][fY2];

		        //좌표값 나열

		        String result=""+realA;
		        result+=realB;
		        result+=realC;
		        result+=realD;
		        result+=realE;
		        result+=realF;

		        String result1=""+realA1;
		        result1+=realB1;
		        result1+=realC1;
		        result1+=realD1;
		        result1+=realE1;
		        result1+=realF1;

		        String result2=""+realA2;
		        result2+=realB2;
		        result2+=realC2;
		        result2+=realD2;
		        result2+=realE2;
		        result2+=realF2;
		      
		      // 최종 결과
		      int OTP = (int )time + Integer.parseInt(result);
		       OTP1 = (int )time + Integer.parseInt(result1);
		       OTP2 = (int )time + Integer.parseInt(result2);
		      if(OTP>1000000) {
		    	  OTP=OTP%1000000;
		      }
		      System.out.println("OTP : "+ OTP +"="+ x);
			return OTP;
		         

	}
    int OTP1() {
    	return OTP1;
    }
    int OTP2() {
    	return OTP2;
    }
    
}

