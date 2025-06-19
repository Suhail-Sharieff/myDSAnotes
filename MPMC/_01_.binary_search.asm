.MODEL  SMALL 
.DATA
    ARR  DW 1,2,3,4,5,6,7
    LEN  EQU ($-ARR)/2 ;total 14 bytes ie 14/2 ie 7 elements
    MSG1 DB "KEY IS FOUND$"
    MSG2 DB "KEY IS NOT FOUND$"
    X    DW 4                      ; Key to search

.CODE
    MAIN PROC
                MOV AX, @DATA
                MOV DS, AX

                ;setting SI as low ptr
                MOV SI, 0H          
                ;setting DI as high ptr
                MOV CX, LEN        ; now CX has 7
                DEC CX             ; now CX has 6 ie last idx in array for "us"
                SHL CX, 1          ; now CS has 12 ie last idx in array for "memory" since we r using 2 byte numbers
                MOV DI, CX         ; DI now points to last idx of array

    SEARCH_LOOP:
                CMP SI, DI
                JA  NOT_FOUND      ; If SI > DI, key not found, JA means Jump if greater

                ; calculate mid ptr
                MOV BX, SI
                ADD BX, DI
                SHR BX, 1          ; BX = (SI + DI)/2 (word offset), BX now has mid ptr

                MOV AX, X
                CMP AX, ARR[BX]    ; Compare key with middle element
                JE  FOUND          ; JE means jump if equal 
                JB  LESS_THAN      ; If key < ARR[BX], search left, JB means jump is less
                JA  MORE_THAN

    MORE_THAN:
                MOV SI, BX
                ADD SI, 2          ; SI = mid + 1 (word offset)
                JMP SEARCH_LOOP

    LESS_THAN:  
                MOV DI, BX
                SUB DI, 2          ; DI = mid - 1 (word offset)
                JMP SEARCH_LOOP

    FOUND:      
                LEA DX, MSG1
                MOV AH, 09H
                INT 21H
                JMP EXIT

    NOT_FOUND:  
                LEA DX, MSG2
                MOV AH, 09H
                INT 21H
                JMP EXIT

    EXIT:       
                MOV AH, 4CH
                INT 21H
MAIN ENDP
END MAIN