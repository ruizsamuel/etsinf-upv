        # the result must be t3=30, t4=25 y t5=35

        .ireg 0,0,0,0,0,10,20 # t1=10, t2=20
        .text
        add t3,t1,t2
        addi t4,t3,-5
        addi t5,t3,5
end:    ori a7,x0,10
        ecall
