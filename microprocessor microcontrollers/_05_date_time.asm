.MODEL SMALL 
.DATA
    MSG1 DB "TIME IS $"
    MSG2 DB 10,13,"DATE IS $"
 
.CODE
MAIN PROC
            MOV  AX,@DATA
            MOV  DS,AX

            LEA  DX,MSG1     ;           PRINT msg1
            MOV  AH,09H
            INT  21H

            MOV  AH,2CH      ;                    Read system time CH = hours, CL = minutes, DH = seconds
            INT  21H

            MOV  AL,CH       ;                      move hours to AL
            CALL PRINT     ;                call PRINT procedure

            MOV  AL,CL       ;                       move minutes to AL
            CALL PRINT

            MOV  AL,DH       ;               Move seconds to DH
            CALL PRINT
 
            LEA  DX,MSG2     ;                  PRINT msg2
            MOV  AH,09H
            INT  21H
 
            MOV  AH,2AH      ;     Read system date  AL = day (sun,mon…) DH =month DL =day CX= year
            INT  21H
            MOV  BX,DX       ;                   save month and day in BX,   BH = month BL=day
            CALL PRINT
 
            MOV  AL,BL       ;                        move day from BL to AL
            CALL PRINT
 
            MOV  AL,BH       ;                        move   month from BH to AL
            CALL PRINT
 
            MOV  DL,':'      ;      PRINT character ‘:’
            MOV  AH,02H
            INT  21H
 
            MOV  AX,CX       ;                    move year to AX=2017
            MOV  DX,0        ;                        DX=0
            MOV  BX,10       ;                       BX=10
            DIV  BX          ;                              DX: AX /  BX   2017 / 10     AX =201 DL =07
            MOV  CL,DL       ;                       move 7 o CL   CL =7
            MOV  DX,0        ;                          DX=0
            DIV  BX          ;                               DX: AX / BX      201 / 10    AX=20  DL =01
            ADD  DL,30H      ;                       DL + 30H = 01 + 30H = 31H PRINT 1
            MOV  AH,02H
            INT  21H
 
            MOV  DL,CL       ;                     DL  CL    DL=07
            ADD  DL,30H      ;                  DL + 30H       07  + 30H = 37H PRINT   7
            MOV  AH,02H
            INT  21H
 
            MOV  AH,4CH
            INT  21H
PRINT PROC
            PUSH AX          ;
            MOV  DL,':'
            MOV  AH,02H
            INT 21H

            POP AX
            AAM
            MOV  DX,AX       ;
            ADD DX,3030H 
            XCHG DH,DL
            MOV  AH,02H
            INT 21H
            
            MOV  DL,DH       ;
            MOV  AH,02H
            INT 21H
            RET
PRINT ENDP
MAIN ENDP
END MAIN