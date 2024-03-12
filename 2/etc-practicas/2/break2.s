          .globl __start
          .text 0x00400000

__start:
          li $s0, 0
          li $s1, 1
loop:
          li $v0,1
          move $a0, $s1
          syscall
          li $v0,11
          li $a0, '>'
          syscall
          li $v0,5
          syscall
          beq $v0, $zero, end
          addu $s0, $s0, $v0
          addi $s1, $s1, 1  
          b loop
end:
          li $v0, 11
          li $a0, '='
          syscall
          li $v0, 1
          move $a0, $s0
          syscall
          li $v0, 11
          li $a0, '\n'
          syscall
          li $v0, 11
          li $a0, 'n'
          syscall
          li $a0, '='
          syscall
          li $v0, 1
          move $a0, $s1
          syscall
          li $v0,10
          move $a0, $s0
          syscall