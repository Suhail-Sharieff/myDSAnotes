MODEL SMALL 
.DATA
    N    DW 6
    R    DW 2
    NCR  DW 0
    MSG        DB 'NCR = $'
    RESULT_BUF DB 6 DUP('$')
    TEN        DW 10
.CODE
MAIN PROC
             MOV  AX, @DATA
             MOV  DS, AX

             MOV  AX, N
             MOV  BX, R

             CALL NCR_PROC

             MOV  AH, 4CH
             INT  21H
ncr_proc PROC
             CMP  AX, BX       ; Compare r and n
             JZ   N1           ; If equAL ADD 1 to result


             CMP  BX, 0        ; If no, check if r = 0
             JZ   N1           ; If yes, ADD 1 to result

             CMP  BX, 1        ; If no, check if r = 1
             JZ   N2           ; If yes, ADD n to result

             MOV  CX, AX
             DEC  CX

             CMP  CX,BX        ; If no, check if r = n-1
             JZ   N2           ; If yes, ADD n to result

             PUSH AX           ; Save n
             PUSH BX           ; Save r
             
             DEC  AX           ; Compute n-1

             CALL ncr_proc     ; CALL ncr_proc

             POP  BX           ; Restore r
             POP  AX           ; Restore n

             DEC  AX           ; Compute n-1
             DEC  BX           ; Compute r-1

            ; ...existing code...
             CALL NCR_PROC

             ; Print message
             LEA DX, MSG
             MOV AH, 09H
             INT 21H

             ; Convert NCR to ASCII and print
             MOV AX, NCR
             LEA DI, RESULT_BUF + 5 ; Point to end of buffer
             MOV CX, 0

PRINT_LOOP:
             XOR DX, DX
             DIV TEN                ; AX = AX / 10, DX = remainder
             ADD DL, '0'
             DEC DI
             MOV [DI], DL
             INC CX
             TEST AX, AX
             JNZ PRINT_LOOP

             LEA DX, [DI]
             MOV AH, 09H
             INT 21H

             MOV  AH, 4CH
             INT 21H

             JMP  LAST
    n1:      ADD  ncr, 1       ; ADD 1 to result
             RET
    n2:      ADD  ncr, AX      ; ADD n to result
    last:    RET
ncr_proc ENDp

MAIN ENDP
END MAIN