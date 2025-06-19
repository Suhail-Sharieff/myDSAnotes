.MODEL  SMALL 
.DATA
    ARR  DW  0004H,0003H,0003H,0002H,0001H
    LEN  EQU ($-ARR)/2
    MSG_BEFORE DB "Before sorting: $"
    MSG_AFTER  DB "After sorting: $"
    SORTED DB 13,10,"Array sorted$",13,10,"$"
    SPACE DB " $"
.CODE
MAIN PROC
        MOV AX,@DATA
        MOV DS,AX

        ; Print before sorting
        LEA DX, MSG_BEFORE
        MOV AH, 09H
        INT 21H
        CALL PRINT_ARRAY

        MOV CL, LEN-1
    OUTER_LOOP: ; uses CL
        MOV SI, OFFSET ARR
        MOV BL, LEN-1
    INNER_LOOP: ; uses BL
        MOV AX, [SI]          ; move first element to AX
        CMP AX, [SI+2]        ; compare with next element
        JB  NEXT              ; if AX < [SI+2], no swap
        JA SWAP               ; else swap
    SWAP:
        MOV DX, [SI+2]        ; swap logic
        MOV [SI+2], AX
        MOV [SI], DX
    NEXT: 
        ADD SI, 2  
        DEC BL                ; decrement comparison count
        JNZ INNER_LOOP

        DEC CL                ; decrement pass count
        JNZ OUTER_LOOP

        ; Print after sorting
        LEA DX, MSG_AFTER
        MOV AH, 09H
        INT 21H
        CALL PRINT_ARRAY

        LEA DX, SORTED
        MOV AH,09H
        INT 21H

        MOV AH,4CH
        INT 21H

;--------------------------
; Print array procedure
;--------------------------
PRINT_ARRAY PROC
        PUSH AX
        PUSH BX
        PUSH CX
        PUSH DX
        PUSH SI

        MOV CX, LEN
        MOV SI, OFFSET ARR
PRINT_LOOP:
        MOV AX, [SI]
        CALL PRINT_NUM
        LEA DX, SPACE
        MOV AH, 09H
        INT 21H
        ADD SI, 2
        LOOP PRINT_LOOP

        POP SI
        POP DX
        POP CX
        POP BX
        POP AX
        RET
PRINT_ARRAY ENDP

;--------------------------
; Print number in AX (unsigned, up to 5 digits)
;--------------------------
PRINT_NUM PROC
        PUSH AX
        PUSH BX
        PUSH CX
        PUSH DX
        PUSH SI

        MOV BX, 10
        LEA SI, NUM_BUF+5
        MOV BYTE PTR [SI], '$'
        DEC SI
        MOV CX, 0
        CMP AX, 0
        JNZ PN_LOOP
        MOV BYTE PTR [SI], '0'
        DEC SI
        INC CX
        JMP PN_DONE
PN_LOOP:
        XOR DX, DX
        DIV BX
        ADD DL, '0'
        MOV [SI], DL
        DEC SI
        INC CX
        CMP AX, 0
        JNZ PN_LOOP
PN_DONE:
        INC SI
        LEA DX, [SI]
        MOV AH, 09H
        INT 21H

        POP SI
        POP DX
        POP CX
        POP BX
        POP AX
        RET
PRINT_NUM ENDP

NUM_BUF DB 6 DUP('$')
MAIN ENDP
END MAIN