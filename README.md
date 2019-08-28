# Operating Systems Java
This is one of the most difficult projects, I have worked on. This project is divided into three phases; in the first phase, I was supposed to make VM Architecture. Inraphical User In the second one, Process and Memory Management. And in the third one, I was supposed to make a Graphical User Interface (GUI) in Java for the whole project. The requirements file for three phases is uploaded in the PDF format.

Project Made By:
----------------

Muhammad Fawad Jawaid Malik (11k-2116)


The Project consist of following classes:

                                              Main class
                                             -----------
Main class executes the arc() class which takes control and execute process.


                                             Arc Class
                                             ----------

Arc class represents the architecture (CPU or Kernel).  
The class has register, instructionset and declares two queues.

The class consists of following functions:

• arc() – constructor

• load()- loads the process file

• debug()- which calls fetch decode() in it

• execute()- executes the process file by updating general purpose registers and also writing a process information in a text file like which instruction is executing (Round Robin scheduling) .

• execute(Process P)- it will perforem execution of some specific process.


• terminate(Proecss P)-  It will terminate the process

• void cflag () - the function that breaks the flag register into bits.

• set_pc() -  It will set the value of program counter

• void Fetch_Decode() - fetches opcode & then fetch the requires data for the execution of  opcode and calls the function according to opcode.

• Functions like BZ, BNZ, BC, BS, JMP, CALL, ACT, MOVL, MOVS, PUSH, POP, RETURN, NOOP, EXIT definition is also provided in this class.

• mksummary()- It makes a text file for all the executing processes  i.e. Summary.txt, which will write about process’s ID, No. of Register-Register Instructions , No. of Register-Immediate Instructions, No. of Memory                           Instructions, No. of Single Operand Instructions, No. of no Operand Instructions and total number of                        instructions. 

• mklogtxt()-  It will make a text file i.e. log.txt

• mkganttchartxt() –It makes a text file i.e. gantt chart.txt



                                                 Process Class
                                                 ---------------

Process class is responsible for all the loading & fetching of any Process from the memory. It consist of PCB  & Dynamic main memory .

Process Class consists following functions:

• Process Class consists following functions:

• Process() - Class constructor

• LoadProcess() - reads from process file and initialize PCB & special  registers. Paged  Segmentation also takes place here.

• getCode() – gets code from main memory located at a particular logical address offset.

• getData() – gets data from main memory located at a  particular logical  address offset.

• setData() – sets data in main memory at a particular logical address offset.

• getFbyte() - gets first 8 bytes of short value 

• getSbyte() - get last 8 bytes of short value

• Concatenate() – concatenate  two bytes into  short


                                                 Memory Class
                                                 -------------

Memory class represents the main memory of the kernel. It consist of the array of 64K for memory & dynamic page table of 64 indexes to keep check of empty frames in memory.
 
It consists of following functions:

• memory() - constructor

• setmem() - setter for memory taking offset  and value as parameters

• getmem4data() - getter of data from memory

• getmem4code() - getter of code from memory

• cload() - load code into memory 

• search() - checks for empty frames in memory using 64 indexes  dynamic pagetable

• getFbyte()  - get first 8 bytes of short value

• getSbyte()  - get last 8 bytes of short value

• Concatenate() - concatenate two byte values 


                                               Register Class
                                               ---------------

There are 16-bit 16 general- purpose & 16-bit special-purpose registers.

It consists of following functions:

•register() – constructor

•void set_spl_reg(short offset, short value) - setter for special  register

•void set_gen_reg(short offset, short value) - setter for general  register

•short get_spl_reg(short offset) - getter for special register

•short get_gen_reg(short offset) - getter for general register

•boolean get_flag_val(short val) - getter for flag register

•It also contains the functions like MOV, ADD, SUB, MUL, DIV, AND, OR, MOVI, ADDI, SUI, MULI, DIVI, ANDI, ORI, SHL, SHR, RTL, RTR, INC, DEC.


                                                    PCB CLASS 
                                                    ---------

PCB class contains variables process pid, process priority, code size, data size , process file name, declaring page tables for data and code. 

PCB class has following functions:-

•getpid()  - It will return process pid.

•getppriority() - It will return process priority.

•getpfilename() - It will return process file name.

•getcodesize() - It will return process code size.

•getdatasize() - It will return process data size.

•get_cpt(int pageno) - It will return frame number allocated to that page number which is  provided as  parameters from code page table.   

•get_dpt(int pageno) -It will return frame number allocated to that  page number which is provided as parameters from data page table.

•setppriority(byte pri) - It will set process priority.

• setpsizec(int szc) -  It will set process code size.

• setpsized(int szd) - It will set process data size.

• set_cpt(int frameno, int pageno) - It will set code page table by sending frame number and page number  as parameters.

• set_dpt(int frameno, int pageno) - It will set data page table by providing frame number and page  number as parameters. 

• PCB()- default constructor

• PCB(byte priority, byte id, String filename, byte sized, byte sizec)  -parametric constructor of PCB class 



	                              INSTRUCTIONSET CLASS
	                              ---------------------

Instruction set components are opcode, reg1, reg2 and immediate.

Instruction set functions are following:

•instructionset() – default constructor which initialize opcode, reg1, reg2 and immediate to zero.

•byte getreg1() - getter for reg1.

•byte getreg2() - getter for reg2.

•short get_immediate() - getter for immediate value.

•void setopcode(byte op) - set opcode value to op.

•void setreg1(byte regs) - set reg1 value to regs.

•void setreg2(byte regs) - set reg2 value to regs.

•void set_immediate(short imme)  - sets  immediate value to imme.

•void shift_immediate() - shifts the immediate value when it needs to be put on a 8 bit reg.

•int check_opcode() -  checks opcode so that the appropriate function may be called or for appropriate memory instruction.  

•The check_opcode() will return 1 for register- register instruction,return 2 for register-Immediate instructions,  
 return 3 for memory instruction, return 4 for single operand instructions and return 5 for no operand instruction.


                                 	       PAGE TABLE CLASS
                                      	-----------------
	
Page table class contains:

An array paget[] of 64, another array of 64 for flag[] and declare integer  variable size to zero.

Page table class functions are following:

•pagetable(int sizze) – parametric constructor and it will set size ,page table and flag.

•int getf(int pageno) – It will take page number as parameter and return it’s  frame number.  

•void setf(int frameno, int pageno) - It will take frame number and  page number as parameters and then set frame number of  
                                    that  particular page number in the page table. It will also set page table  flag to 1.
*int setflag(int i) - this sets flag to the page.

*int delf(int i) - this deleted the page allocated.

                                  QUEUE
                               -----------

*item dequeue() - function to dequeue 

*void enqueue(item item) - function to enqueue

*boolean isEmpty() - check if the queue is empty

*iterator<item> iterator() 

*item peek()

*int size() - return queue size

*string toString() 



                                   




                                       NEXUS
                                   ----------------

 This is the swing GUI - drag and drop concept of user interface



                            
                                   MEMORYDUMP
                              ----------------------

The GUI interface for memory dump.

                                
                                   GUI
                                 -------

*blockbuttonActionPerformed(ActionEvent evt) - press the button to block the process

*debuggbuttionActionPerformed(ActionEvent evt) - press the button to debugg the process

*executeActionPerformed(ActionEvent evt) -  to execute the process

*executeallbuttonActionPerformed(ActionEvent evt) - press the button to all processes


*killbuttonActionPerformed(ActionEvent evt) - press the button to kill a process

*loadbuttonActionPerformed(ActionEvent evt) - press the button to load the process

*main(string[] args)

*memdumpbuttonActionPerformed(ActionEvent evt) - press the button for viewing memory dump

*refreshbuttonActionPerformed(ActionEvent evt) - to refresh memory details press the button

*shutdownbuttonActionPerformed(ActionEvent evt) - press to button to shutdown the OS

*unblockbuttonActionPerformed(ActionEvent evt) - press the button to unblock a process
