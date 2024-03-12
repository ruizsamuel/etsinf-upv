          .globl __start
          .data 0x10000000
demana:   .asciiz "Escribe alguna cosa: "
cadena:   .space 80             
res:      .asciiz "Ha escrito: "
len:      .asciiz "La longitud es: "

          .text 0x00400000
__start:  # Solicita cadena
          la $a0, demana        
          la $a1, cadena        
          li $a2, 80            
          jal InputS   
        
          # Imprime cadena
          la $a0, res        
          la $a1, cadena 
          jal OutputS

          # Imprime longitud
          la $a0, len
          li $v0, 4
          syscall
          la $a0, cadena
          jal StrLen
          move $a0, $v0
          li $v0, 1
          syscall

          li $v0,10
          syscall

# Solicita al usuario una cadena y la almacena en la dirección apuntada por $a1, con una longitud máxima ($a2)
InputS:   li $v0, 4
          syscall
          li $v0, 8
          move $a0, $a1
          move $a1, $a2
          syscall
          jr $ra

# Imprime en pantalla las cadenas apuntadas por $a0 y $a1
OutputS:  li $v0, 4
          syscall
          move $a0, $a1
          li $v0, 4
          syscall

# Devuelve en $v0 la longitud de la cadena de caracteres a la que apunta $a0
StrLen:   li $v0, -2            # El contador de caracteres comienza en -2 para descontar el carácter LF y el final de línea        
Buc:      lb $t2, ($a0)
          addi $v0, $v0, 1
          addi $a0, $a0, 1
          bne $t2, $zero, Buc
          jr $ra