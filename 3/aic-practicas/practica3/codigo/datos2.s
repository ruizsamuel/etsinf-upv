        # the result must be t3=10, t4=5 y t5=15

        .data
a:      .dword 10
        .text
        ld t3,a(gp)
        addi t4,t3,-5
        addi t5,t3,5
end:    ori a7,x0,10
        ecall
