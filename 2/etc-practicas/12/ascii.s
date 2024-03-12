### PROGRAMA BASE PRACTICAS DE ENTRADA/SALIDA MIPS R2000

#-------------------------------------------------#
#
#  PRÁCTICA 12: SINCRONIZACIÓN POR PRUEBA DE ESTADO
# 
#-------------------------------------------------#

# ACTIVIDAD 2:  Añade la instrucción que lee el registro de datos
#               del teclado en el lugar indicado:


#------- Segmento de datos ---------#
	.data		
T1:	.asciiz "Pulse . para finalizar\n"
T2: .asciiz "\nFIN\n"

#------- Segmento de código ---------#
	.text
    .globl __start	

__start:
	# Escribe en consola mensaje T1
	li $v0, 4
	la $a0, T1
	syscall	
	
	# Carga dir base teclado
	la $t0, 0xffff0000

bucle:
    lw $t1, 0($t0)
    andi $t1, $t1, 1
    beqz $t1, bucle

    li $t3, '.'

    lw $t2, 4($t0)

    beq $t2, $t3, fin
    
    li $v0, 11
    move $a0, $t2
    syscall
    j bucle

fin:

    li $v0, 4
	la $a0, T2
	syscall	

    # exit
	li $v0, 10
	syscall			

	.end