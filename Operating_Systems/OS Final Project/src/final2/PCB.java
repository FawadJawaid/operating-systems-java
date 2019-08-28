/*
 * PCB CLASS
 * 
 */

package final2;

/**
 *
 * Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 */
public class PCB { //PCB class

             int pid;
             byte ppriority;
             int psizec;
             int psized;
             String pfilename;
             register reg;
             pagetable datapt= new pagetable(20);
             pagetable codept= new pagetable(20);
             int currentcount; // current pc
             int regreg;        //
             int regimd;          //
             int mem;               // counter for text files (keeping count of specific instructions)
             int sgop;            //
             int noop;         //
             int instcounter; // instruction counter
             int debug;

             PCB(){ //constructor
               reg = new register();
               currentcount=0;
               regreg =0;
               regimd=0;
               mem=0;
               sgop=0;
               noop=0;
               instcounter=1;
               debug=0;
             }

     void incdebug(){ //inc debug
     debug++;
     }

     int getdebug(){ //get debug
     return debug;
     }

     int getinstcounter(){ //getter for instruction counter
     return instcounter;
     }

     void incinstcounter(){ //incrementer for instruction counter
     instcounter++;
     }

     int getregreg(){ //getter for reg-reg instruction counter
     return regreg;
     }

     int getregimd(){//getter for reg-immediate instruction counter
     return regimd;
     }

     int getmem(){// getter for mem instruction counter
     return mem;
     }

     int getsgop(){ //getter for single operand  instruction counter
     return sgop;
     }

     int getnoop(){ //getter for noop operand instruction counter
     return noop;
     }

     void incregreg(){ //incrementer for reg-reg instruction counter
       regreg++;
     }

     void incregimd(){ //incrementer for reg-immediate instruction counter
      regimd++;
     }

     void incmem(){ //incrementer for memory instruction counter
     mem++;
     }

     void incsgop(){//incrementer for single operand instruction counter
     sgop++;
     }

     void incnoop(){//incrementer for no operand instruction counter
     noop++;
     }


     int getcount(){ //getter for current pc
      return currentcount;
     }

     void setcount(int count){//setter for current count
      currentcount =count;
     }

      int getpid() //getter for pid
     {
          return pid;
      }

       byte getppriority() //getter for priority
      {
        return ppriority;
       }


       void setpid(int pi) //setter for pid
      {
       pid=pi;
       }
        void setppriority(byte pri) //setter for priority
       {
            ppriority=pri;
        }


       void setpsizec(int szc) //setterprocess code size
      {
        psizec=szc;
       }

        void setpsized(int szd) // setter for data size
      {
       psized=szd;
       }

       String getpfilename() //getter for file name
      {
       return pfilename;
       }

       void setpfilename(String pp) //setter for filename
      {
       pfilename=pp;
       }
     
      int getcodesize(){return psizec;} //getter for code size
      int getdatasize(){return psized;} //getter for data size



      PCB(byte priority, byte id, String filename, byte sized, byte sizec){ //constructor
               pid=id;
               ppriority=priority;
               pfilename=filename;
               psizec=sizec;
               psized=sized;

      }

      void set_cpt(int frameno, int pageno){ //setter for code page table
      codept.setf(frameno,pageno);
      }

      void set_dpt(int frameno, int pageno){ //setter for data page table
      datapt.setf(frameno,pageno);
      }

      int get_cpt(int pageno){ //getter for code page table
      return codept.getf(pageno);
      }

      int get_dpt(int pageno){ //getter for data page table
      return datapt.getf(pageno);
      }

void printt(){ //print data & code page table
datapt.print_table();
System.out.println("     ");
codept.print_table();
}
}
