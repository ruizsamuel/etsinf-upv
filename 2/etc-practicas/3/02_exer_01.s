          .globl __start
          .text 0x00400000
__start:  li $v0,5                  # Carga el inmediato 5 en el registro v0
          syscall                   # Llamada al sistema (5) -> Guarda un entero de la entrada estándar
          move $a0,$v0              # Mueve el resultado de la llamada al sistema a a0
          li $v0,5                  # Carga el inmediato 5 en el registro v0
          syscall                   # Llamada al sistema (5) -> Guarda un entero de la entrada estándar
          move $a1,$v0              # Mueve el resultado de la llamada al sistema a a1
          jal Mult                  # Salta a la dirección de la etiqueta "Mult"
          move $a0,$v0              # Mueve a a0 el resultado de la "función" (v0)
          li $v0,1                  # Carga el inmediato 1 en el registro v0 para imprimir a0
          syscall                   # Llamada al sistema (1) imprime a0
          li $v0,10                 # Termina el programa
          syscall                   # Llamada al sistema para terminar
          
Mult:     li $v0, 0                 # Limpia el registro v0 (donde se va a realizar el sumatorio)
          beqz $a1, MultRet         # En caso de que a1 sea 0, salta a "MultRet", para retornar a la dirección de retorno del jal anterior
MultFor:  add $v0, $v0, $a0         # Suma el valor de a0 a lo que haya en v0
          addi $a1, $a1, -1         # Resta uno a a1 (sería el contador de veces que hay que umar a0 a v0)
          bne $a1, $zero, MultFor   # Si a1 es diferente a cero (hay que continuar sumando), salta a MultFor (inicio del bucle) 
MultRet:  jr $ra                    # Salta a la dirección de retorno que se almacenó en ra cuando se ejecutó la instrucción jal