/*
 *
 * MAIN ARCHITECHTURE CLASS DESCRIPTION
 *
 */

package final2;
import java.io.*;


/*
 * Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 */

public class arc { //class architecture
//register reg=new register();
memory mainmemory;//=new memory();
instructionset ir=new instructionset();
boolean carry, zero, sign, overflow;
int i=0;
int p=0;
//pagetable codetableinCPU;
//pagetable datatableinCPU;
Process p1;
queue <Process>q1 = new queue<Process>();  // waiting queue
queue <Process>q2 = new queue<Process>(); //queue for GUI
queue <Process>sp = new queue<Process>(); //queue for summary process
queue <Process>blk= new queue<Process>(); //queue for block process



             arc()throws Exception{ //contructor
             carry=false;
             zero=false;  //default value of flag register bits
             sign=false;
             overflow=false;
             } // constructor ends


             void load(String filename)throws Exception{ //loads file
             p1 = new Process();
             p1.LoadProcess(filename);
             String pid=String.valueOf(p1.pcb.getpid());
             String log="Process ID "+pid+" is loaded";
             mklogtxt(log);
             q1.enqueue(p1);
             q2.enqueue(p1);
             sp.enqueue(p1);
             } //file loading ends


             void debug(Process p)throws Exception{
              p.pcb.reg.set_spl_reg((short)0,(short) 0);     //update gen purpose register
              p.pcb.reg.set_spl_reg((short)1,(short) 0);
              p.pcb.reg.set_spl_reg((short)2,(short)p.pcb.getcodesize());
              p.pcb.reg.set_spl_reg((short)3,(short)p.pcb.getdebug() );
              p.pcb.reg.set_spl_reg((short)4,(short) 0);
              p.pcb.reg.set_spl_reg((short)5,(short) 0);
              p.pcb.reg.set_spl_reg((short)6,(short) 0);
              p.pcb.reg.set_spl_reg((short)7,(short) 0);
              p.pcb.reg.set_spl_reg((short)8,(short)p.pcb.getdatasize());
              Fetch_Decode();
             }

             void execute(Process pro)throws Exception{
              pro.pcb.reg.set_spl_reg((short)0,(short) 0);     //update gen purpose register
              pro.pcb.reg.set_spl_reg((short)1,(short) 0);
              pro.pcb.reg.set_spl_reg((short)2,(short)pro.pcb.getcodesize());
              pro.pcb.reg.set_spl_reg((short)3,(short)pro.pcb.getcount() );
              pro.pcb.reg.set_spl_reg((short)4,(short) 0);
              pro.pcb.reg.set_spl_reg((short)5,(short) 0);
              pro.pcb.reg.set_spl_reg((short)6,(short) 0);
              pro.pcb.reg.set_spl_reg((short)7,(short) 0);
              pro.pcb.reg.set_spl_reg((short)8,(short)pro.pcb.getdatasize());
                 short c,c1;
                 do{
              i+=2;
              p+=2;
              c=pro.pcb.reg.get_spl_reg((short)3);   //counter for code
              c1=pro.pcb.reg.get_spl_reg((short)2);
                  Fetch_Decode();
              String pid=String.valueOf(p1.pcb.getpid());       // writing in text files
              String insno=String.valueOf(p1.pcb.getinstcounter());
              p1.pcb.incinstcounter();
              String log=pid+": Instruction # "+insno+" executing";
              mklogtxt(log);

              String gantt="Clock Cycle :"+p+" - "+pid;
              mkganttchartxt(gantt);
                 }while(c<c1-1);

                pro.pcb.reg.set_spl_reg((short)3, (short)0);
                terminate(pro);
             }


             void execute() throws Exception{ //execute all functions
             //p1.LoadProcess("p3.proc");
             //p1.LoadProcess("p2.proc");
             short c,c1;
            
             System.out.println("execution starts");
             
          while(!q1.isEmpty()){
               i+=4;

              p1=q1.dequeue();
              p1.pcb.reg.set_spl_reg((short)0,(short) 0);     //update gen purpose register
              p1.pcb.reg.set_spl_reg((short)1,(short) 0);
              p1.pcb.reg.set_spl_reg((short)2,(short)p1.pcb.getcodesize());
              p1.pcb.reg.set_spl_reg((short)3,(short)p1.pcb.getcount() );
              p1.pcb.reg.set_spl_reg((short)4,(short) 0);
              p1.pcb.reg.set_spl_reg((short)5,(short) 0);
              p1.pcb.reg.set_spl_reg((short)6,(short) 0);
              p1.pcb.reg.set_spl_reg((short)7,(short) 0);
              p1.pcb.reg.set_spl_reg((short)8,(short)p1.pcb.getdatasize());

              p+=2;

              c=p1.pcb.reg.get_spl_reg((short)3);   //counter for code
              c1=p1.pcb.reg.get_spl_reg((short)2);  //limit of code
              Fetch_Decode();

              String pid=String.valueOf(p1.pcb.getpid());       // writing in text files
              String insno=String.valueOf(p1.pcb.getinstcounter());
              p1.pcb.incinstcounter();
              String log=pid+": Instruction # "+insno+" executing";
              mklogtxt(log);
              
              String gantt="Clock Cycle :"+p+" - "+pid;
              mkganttchartxt(gantt);


              p+=2;
              c=p1.pcb.reg.get_spl_reg((short)3);  //counter for code
              c1=p1.pcb.reg.get_spl_reg((short)2);  //counter for limit
              Fetch_Decode();

              insno=String.valueOf(p1.pcb.getinstcounter());   //writing in text files
              p1.pcb.incinstcounter();
              log=pid+": Instruction # "+insno+" executing";
              mklogtxt(log);

              gantt="Clock Cycle :"+p+" - "+pid;
              mkganttchartxt(gantt);

              if(c<c1-1){q1.enqueue(p1); p1.pcb.setcount(p1.pcb.reg.get_spl_reg((short)3)); } //cgk if func ends
              else{terminate(p1);}

              System.out.println(p1.pcb.getpfilename());

              log="Context Switching";
              mklogtxt(log);
           } //execute func ends

 p1.pcb.reg.set_spl_reg((short)3, (short)0);
    }


             void terminate(Process p){ //terminate func
             int i=0;
             while(i<20){
                 if(p.pcb.codept.getflag(i)==1){int f=p.pcb.codept.delf(i); p.mem1.memtable.setf(0,f);}
                 i++;
             }
             i=0;
             while(i<20){
                 if(p.pcb.datapt.getflag(i)==1){int f=p.pcb.datapt.delf(i); p.mem1.memtable.setf(0,f);}
                 i++;
             }
             Process p2=new Process();
             for(int c=0; c<q1.size(); c++){
             p2=q1.dequeue();
             if(p2.pcb.pid!=p.pcb.pid){q1.enqueue(p2);}
             else break;
             }

             for(int c=0; c<q2.size(); c++){
             p2=q2.dequeue();
             if(p2.pcb.pid!=p.pcb.pid){q2.enqueue(p2);}
             else break;
             }


             } //terminate

             

             void cflag () //the function that breaks the flag register into bits
             {
                 short val1=0; //value of carry in 0th bit of flag register
                 short val2=1; //value of carry in 1st bit of flag register
                 short val3=2; //value of carry in 2nd bit of flag register
                 short val4=3; //value of carry in 3rd bit of flag register
                 carry= p1.pcb.reg.get_flag_val(val1);  //getting value from flag register
                 zero=p1.pcb.reg.get_flag_val(val2);
                 sign=p1.pcb.reg.get_flag_val(val3);
                 overflow=p1.pcb.reg.get_flag_val(val4);
                  
             }

             void  BZ (byte number) //register imediate branch if zero ins
             {
                   cflag();
                   short val=3; //code counter indexed in 3rd position of special_purpose register
                   if (zero == false)
                   p1.pcb.reg.set_spl_reg(val,number);
             }

             void  BNZ (byte number) //register imediate branch if not zero ins
             {
                   cflag();
                   short val=3; //code counter indexed in 3rd position of special_purpose register
                   if (zero != false)
                  p1.pcb.reg.set_spl_reg(val,number);
             }

             void  BC (byte number) //register imediate branch if carry ins
             {
                   cflag();
                   short val=3; //code counter indexed in 3rd position of special_purpose register
                   if (carry == true)
                   p1.pcb.reg.set_spl_reg(val,number);
             }

             void  BS (byte number) //register imediate branch if sign ins
             {
                   cflag();
                   short val=3;//code counter indexed in 3rd position of special_purpose register
                   if (sign == true)
                   p1.pcb.reg.set_spl_reg(val,number);
             }

             void  JMP (byte number) //register imediate jump ins
             {
                 short val=3; //code counter indexed in 3rd position of special_purpose register
                 p1.pcb.reg.set_spl_reg(val,number);
             }

             void  CALL (byte add)// register imediate call instruction
             {
                 short val=3; //code counter indexed in 3rd position of special_purpose register
                 //  push(reg.get_spl_reg(val));
                   p1.pcb.reg.set_spl_reg(val,add);
             }

             void ACT(byte add) //register imediate act instruction
             {
             short val=3; //code counter indexed in 3rd position of special_purpose register
             p1.pcb.reg.set_spl_reg(val,add);
             }

             void MOVL(byte  r1,short offset) throws Exception // memory load instructions
             {

             if(offset<p1.pcb.reg.get_spl_reg((byte)8)){
             short value = p1.getData(offset);
             p1.pcb.reg.MOVI(r1,value);
             }
 else{

  mklogtxt("%%% Trap generated in Process "+p1.pcb.getpid()
          +" due to memory out of bound");

 
 }

             }
             
             
             void MOVS(byte r1,short offset) throws Exception// memory store instructions
             {
                 byte val1=1;//code base indexed in 1st position of special_purpose register
                 byte val2=2;//code limit indexed in 2nd position of special_purpose register
                 short s = r1;
                 short value= p1.pcb.reg.get_gen_reg(s);
                 if(offset<p1.pcb.reg.get_spl_reg((byte)8))
                 p1.setData(offset, r1);
                  else
                  {
                  mklogtxt("%%% Trap generated in Process "+p1.pcb.getpid()
          +" due to memory out of bound");
                  }
             }

             
          /* IGNORED
             void push(byte r1) // operand push instructions
             {
                 byte val1=4; //stack base indexed in 4th position of special_purpose register
                 byte val2=9; //flag indexed in 9th position of special_purpose register
                 byte val3=6; //stack limit indexed in 6th position of special_purpose register
                 short val4=reg.get_spl_reg(val3) ;
                 val4 =+1;
             if (reg.get_spl_reg(val1)+reg.get_spl_reg(val2) > reg.get_spl_reg(val3))
             {
             short val = reg.get_gen_reg(r1);
             val >>= 8;  //shifting 8 bits
             mainmemory.pushtostack(val4,val);
             mainmemory.pushtostack(val4,reg.get_gen_reg(r1));
             }else{
                  System.out.println("stack out of bound ");
                  EXIT();
                  }
       }

             byte pop() // single operand pop instrctuions
             {
                 byte val1=6; //stack counter indexed in 6th position of special_purpose register
                 byte val2=val1--;
             if(reg.get_spl_reg(val1)>0) //checking if condition
             return(mainmemory.popfromstack(reg.get_spl_reg(val2)));
             else {
                  System.out.println("stack empty");
                  EXIT();
                  }
             return(0);
             }

*/
             void set_pc(byte val){ //set the pc
                byte val1=3;//code counter indexed in 3rd position of special_purpose register
                p1.pcb.reg.set_spl_reg(val1,val); //setting value of special register at val(th)
              }


             void RETURN() //no operand return instruction
             {
             byte a;
             byte val1=6; //stack counter indexed in 6th position of special_purpose register
         //    a= mainmemory.popfromstack(reg.get_spl_reg(val1));
             p1.pcb.reg.DECS(val1);
       //      a <<= 8;
        //     byte b = mainmemory.popfromstack(reg.get_spl_reg(val1));
         //    a  += b;
             p1.pcb.reg.DECS(val1);
        //     reg.MOVIS(val1,a);
             }

             void NOOP() //no operand no operation instruction
             {}


            void EXIT() // no operand exit instruction
             {
                  terminate(p1);
             }

/**                 Fawad Jawaid                **/

             void Fetch_Decode() throws Exception//perofrms the fetch operation and decoding
             {
                byte val1=3;//code counter indexed in 3rd position of special_purpose register
                short val2 = p1.pcb.reg.get_spl_reg(val1);
               // System.out.println("pc:" + reg.get_spl_reg(val1) + " val2 ;" +val2);
             //   p1.getCode(val2);
             ir.setopcode(p1.getCode(val2)); //getting opcode from memory
                p1.pcb.reg.INCS(val1); //increment PC
             int check=ir.check_opcode();


             if(check==1) //all register-register instruction
                  {
                  p1.pcb.incregreg();
                  val2 = p1.pcb.reg.get_spl_reg(val1);
                  byte rc1=p1.getCode(val2); // getting register address from instruction set
                 ir.setreg1(rc1);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  val2=p1.pcb.reg.get_spl_reg(val1);
                  rc1=p1.getCode(val2); // getting register address from instruction set
                  ir.setreg1(rc1);
                  p1.pcb.reg.INCS(val1);   //increment PC


                  if (ir.opcode==20) //opcode for MOV in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.MOV(r1, r2);
                  System.out.println("MOV");
                  }

                  if (ir.opcode==24) //opcode for ADD in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.ADD(r1, r2);
                  System.out.println("ADD");
                  }


                  if (ir.opcode==26) //opcode for SUB in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.SUB(r1, r2);
                  System.out.println("SUB");
                  }

                  if (ir.opcode==28) //opcode for MUL in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.MUL(r1, r2);
                  System.out.println("MUL");
                  }
                  
                  if (ir.opcode==30) //opcode for DIV in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.DIV(r1, r2);
                  System.out.println("DIV");
                  }

                  if (ir.opcode==32)//opcode for AND in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.AND(r1, r2);
                  System.out.println("AND");
                  }

                  if (ir.opcode==34)//opcode for OR in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=ir.getreg2();
                  p1.pcb.reg.OR(r1, r2);
                  System.out.println("OR");
                  }
                }


             else if(check==2)//register immediate instruction
                  {
                 p1.pcb.incregimd();
                 val2 = p1.pcb.reg.get_spl_reg(val1);
                  byte rc1=p1.getCode(val2); // getting register address from instruction set
                 ir.setreg1(rc1);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  //val2=reg.get_spl_reg(val1);
                  short imme;
                  imme=p1.getData(val2); // getting register address from instruction set
                  ir.set_immediate(imme);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  p1.pcb.reg.INCS(val1);   //increment PC

                    if (ir.opcode==21) //opcode for MOVI in decimel
                  { 
                  byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.MOVI(r1, r2);
                  System.out.println("MOVI");
                  }
                  
                  if (ir.opcode==25)//opcode for ADDI in decimel
                  { byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.ADDI(r1, r2);
                  System.out.println("ADDI");
                  }

                  if (ir.opcode==27)//opcode for SUBI in decimel
                  { byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.SUBI(r1, r2);
                  System.out.println("SUBI");
                  }

                  if (ir.opcode==29)//opcode for MULI in decimel
                  { byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.MULI(r1, r2);
                  System.out.println("MULI");
                  }

                  if (ir.opcode==31)//opcode for DIVI in decimel
                  { if(ir.get_immediate()!=0){
                       byte r1=ir.getreg1();
                    byte r2=(byte)ir.get_immediate();
                    p1.pcb.reg.DIVI(r1, r2);}
                   else
                   EXIT();
                    System.out.println("DIVI");
                  }

                  if (ir.opcode==33)//opcode for ANDI in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.ANDI(r1, r2);
                  System.out.println("ANDI");
                  }

                  if (ir.opcode==35)//opcode for ORI in decimel
                  {
                  byte r1=ir.getreg1();
                  byte r2=(byte)ir.get_immediate();
                  p1.pcb.reg.ORI(r1, r2);
                  System.out.println("ORI");
                  }

                  if (ir.opcode==50)//opcode for BZ in decimel
                  { BZ((byte)ir.get_immediate());System.out.println("BZ");}

                  if (ir.opcode==51)//opcode for BNZ in decimel
                  { BNZ((byte)ir.get_immediate());System.out.println("BNZ");}
                  
                  if (ir.opcode==52)//opcode for BC in decimel
                  { BC((byte)ir.get_immediate());System.out.println("BC");}

                  if (ir.opcode==53) //opcode for BS in decimel
                  { BS((byte)ir.get_immediate());System.out.println("BS");}

                  if (ir.opcode==53) //opcode for JMP in decimel
                  { JMP((byte)ir.get_immediate());System.out.println("JMP");}

                  if (ir.opcode==64) //opcode for CALL in decimel
                  { CALL((byte)ir.get_immediate());System.out.println("CALL");}

                  if (ir.opcode==81) //opcode for ACT in decimel
                  { ACT((byte)ir.get_immediate());System.out.println("ACT");}
                  }


             if(check==3) // memory instruction
             {
                 p1.pcb.incmem();
                 val2 = p1.pcb.reg.get_spl_reg(val1);
                  byte rc1=p1.getCode(val2); // getting register address from instruction set
                 ir.setreg1(rc1);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  //val2=reg.get_spl_reg(val1);
                  short imme;
                  imme=p1.getData(val2); // getting register address from instruction set
                  ir.set_immediate(imme);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  p1.pcb.reg.INCS(val1);   //increment PC

                  if (ir.opcode==22)//opcode for MOVL in decimel
                  { MOVL(ir.getreg1(),(byte)ir.get_immediate());System.out.println("MOVL");}

                  if (ir.opcode==23)//opcode for MOVS in decimel
                  { MOVS(ir.getreg1(),(byte)ir.get_immediate());System.out.println("MOVS");}

                  }


             if(check==4)//single operand instruction
                  {
                 p1.pcb.incsgop();
                  val2 = p1.pcb.reg.get_spl_reg(val1);
                  byte rc1=p1.getCode(val2); // getting register address from instruction set
                 ir.setreg1(rc1);
                  p1.pcb.reg.INCS(val1);   //increment PC
                  //val2=reg.get_spl_reg(val1);
                //  short imme;
                //  imme=p1.getData(val2); // getting register address from instruction set
                 // ir.set_immediate(imme);
                  //reg.INCS(val1);   //increment PC
                 // reg.INCS(val1);   //increment PC
                

                  if( ir.opcode == 36)//opcode for SHL in decimel
                  { byte r1=ir.getreg1();
                      p1.pcb.reg.SHL(r1);
                      System.out.println("SHL");
                  }

                  if( ir.opcode == 37)//opcode for SHR in decimel
                  {  byte r1=ir.getreg1();
                      p1.pcb.reg.SHR(r1);
                      System.out.println("SHR");
                  }

                  if( ir.opcode == 38)//opcode for RTL in decimel
                  {  byte r1=ir.getreg1();
                      p1.pcb.reg.RTL(r1);
                  System.out.println("RTL");
                  }

                  if( ir.opcode == 39)//opcode for RTR in decimel
                  {  byte r1=ir.getreg1();
                      p1.pcb.reg.RTR(r1);
                  System.out.println("RTR");
                  }

                  if( ir.opcode == 40)//opcode for INC in decimel
                  {  byte r1=ir.getreg1();
                      p1.pcb.reg.INC(r1);
                  System.out.println("INC");
                  }

                  if( ir.opcode == 41)//opcode for DEC in decimel
                  {  byte r1=ir.getreg1();
                      p1.pcb.reg.DEC(r1);
                  System.out.println("DEC");
                  }

                  if( ir.opcode == 42)//opcode for push in decimel
                  {  byte r1=ir.getreg1();
                     // push(r1);
                  System.out.println("PUSH");
                  }

                  if( ir.opcode == 43)//opcode for POP in decimel
                  {//ir.setreg1(pop());
                  System.out.println("POP");
                  }

                  }


             if(check==5)//no operand instruction
                  {
                   p1.pcb.incnoop();
                  if( ir.opcode == 65) //opcode for RETURN in decimel
                  { RETURN(); System.out.println("RETURN");}

                  if( ir.opcode == 47)//opcode for NOOP in decimel
                  { NOOP(); System.out.println("NOOP");}

                  if( ir.opcode == 255)//opcode for EXIT in decimel
                  { EXIT(); System.out.println("EXIT");}

                  }

             mklogtxt("%%% Trap generated in Process "+p1.pcb.getpid()
          +" Invalid Opcode");
              

           }

             void mksummary(){ // making Process-Summary.txt
   try{
   BufferedWriter out = new BufferedWriter(new FileWriter("Process Summary.txt"));
 Process pw=new Process();
   while(!sp.isEmpty()){
       pw=sp.dequeue();
       out.write("Process ID: "+pw.pcb.getpid()+"\r\n");
       out.write("=================="+"\r\n"+"\r\n");
       out.write("No. of Register-Register Instructions: "+pw.pcb.getregreg()+"\r\n");
       out.write("No. of Register-Immediate Instructions: "+pw.pcb.getregimd()+"\r\n");
       out.write("No. of Memory Instructions: "+pw.pcb.getmem()+"\r\n");
       out.write("No. of Single Operand Instructions: "+pw.pcb.getsgop()+"\r\n");
       out.write("No. of No Operand Instructions: "+pw.pcb.getnoop()+"\r\n");
       int total=pw.pcb.getregreg()+pw.pcb.getregimd()+pw.pcb.getmem()+pw.pcb.getsgop()+
               pw.pcb.getnoop();
       out.write("Toatal No. of Instructions: "+total+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n");

   }

   out.close();
                 }catch(Exception e){}
             } //ends



  void mklogtxt(String s)throws Exception{ // making log.txt
  BufferedWriter out1 = new BufferedWriter(new FileWriter("log.txt",true));
  out1.write(s+"\r\n"+"\r\n");
out1.close();
  } //ends


  void mkganttchartxt(String s)throws Exception{ //making gantt chart.txt
  BufferedWriter out2 = new BufferedWriter(new FileWriter("Gantt Chart.txt",true));
  out2.write(s+"\r\n"+"\r\n");
  out2.close();
  } //ends


}
