##########################################################
# Segmento de datos
##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "

##########################################################
# Segmento de código
##########################################################

                .globl __start
                .text 0x00400000

__start:        la $a0, reloj
                
                # --------------- inicializa_reloj --------------

                li $a1, 0x11173B3B
                jal inicializa_reloj
                
                # --------------- inicializa_reloj_alt ---------

                #li $a1, 0x0000000A
                #li $a2, 0x00000002
                #li $a3, 0x00000007
                #jal inicializa_reloj_alt

                # ------------- inicializa_reloj_hh_mm_ss ------

                #li $a1, 0x07
                #jal inicializa_reloj_hh

                #li $a1, 0x02
                #jal inicializa_reloj_mm

                #li $a1, 0x0A
                #jal inicializa_reloj_ss

                jal imprime_reloj

                # ------- imprime segundos -----------
                la $a0, reloj
                jal devuelve_reloj_en_s
                move $a0, $v0
                jal imprime_s

                # --- inicializa reloj con segundos --
                la $a0, reloj
                li $a1, 66765
                jal inicializa_reloj_en_s
                la $a0, reloj
                jal imprime_reloj

        
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
                .end

inicializa_reloj:
                sw $a1, 0($a0)

                # Bits no definidos = 0
                lw $t0, 0($a0)
                li $t1, 0x001F3F3F # Máscara de bits
                and	$t0, $t0, $t1
                
                sw $t0, 0($a0) 
                
                jr $ra

inicializa_reloj_alt:
                sll $a2, $a2, 8
                sll $a3, $a3, 16

                or $a1, $a2, $a1
                or $a1, $a3, $a1

                sw $a1, 0($a0)

                jr $ra

inicializa_reloj_hh:
                sb $a1, 2($a0)  
                jr $ra
inicializa_reloj_mm:
                sb $a1, 1($a0)  
                jr $ra
inicializa_reloj_ss:
                sb $a1, 0($a0)  
                jr $ra


inicializa_reloj_en_s:
                li $t0, 3600
                li $t1, 60

                div $a1, $t0
                mfhi $t2
                mflo $t3 # HH
                div $t2, $t1
                mfhi $t2 ## SS
                mflo $t4 # MM

                sll $t4, $t4, 8
                sll $t3, $t3, 16

                or $t2, $t4, $t2
                or $t2, $t3, $t2

                sw $t2, 0($a0)

                jr $ra                


########################################################## 
# Subrutina que imprime el valor del reloj
# Entrada: $a0 con la dirección de la variable reloj
##########################################################

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                la $a0, cad_horas       # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_minutos     # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_segundos    # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra


########################################################## 
# Subrutina que calcula los segundos de una hora
# Entrada: $a0 con la hora
# Salida: $v0 con los segundos calculados
##########################################################

devuelve_reloj_en_s:
                lbu $v0, 0($a0)
                lbu $t1, 1($a0)
                lbu $t2, 2($a0)

                li $t4, 60
                mult $t4, $t1
                mflo $t1
                mfhi $t4
                bnez $t4, salir
                addu $v0, $v0, $t1

                li $t4, 3600
                mult $t4, $t2
                mflo $t2
                mfhi $t4
                bnez $t4, salir
                addu $v0, $v0, $t2

                jr $ra

########################################################## 
# Subrutina que imprime los segundos calculados
# Entrada: $a0 con los segundos a imprimir
########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall


                la $a0, cad_reloj_en_s  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra
                

########################################################## 
# Subrutina que incrementa el reloj en una hora
# Entrada: $a0 con la dirección del reloj
# Salida: reloj incrementado en memoria
# Nota: 23:MM:SS -> 00:MM:SS
########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra
