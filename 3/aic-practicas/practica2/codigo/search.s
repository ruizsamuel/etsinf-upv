        .data
a:      .dword  9,8,0,1,0,5,3,1,2,0
tam:    .dword 10         # Vector size
cont:   .dword 0          # Nr of elements == 0

        .text
start:  addi t0,gp,a      # Pointer
        ld t1,tam(gp)     # Vector size
        add t2,zero,zero  # Counter of zeros
 
loop:   ld t3, 0(t0)  
        addi t1, t1, -1
        addi t0, t0, 8
        bne t3, zero, loop
        beq t1, zero, final
        addi t2, t2, 1
        j loop

final:  bne t3, zero, exit
        addi t2, t2, 1
exit:   sd t2, cont(gp)
        ori x17,x0,10     # Exit system call
        ecall