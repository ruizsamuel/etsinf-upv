        # z = a*x + y
        # vector size: 60 elements
        # vector x
	.data
x:      .double 0,1,2,3,4,5,6,7,8,9
        .double 10,11,12,13,14,15,16,17,18,19
        .double 20,21,22,23,24,25,26,27,28,29
        .double 30,31,32,33,34,35,36,37,38,39
        .double 40,41,42,43,44,45,46,47,48,49
        .double 50,51,52,53,54,55,56,57,58,59

	# vector y
y:      .double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100
	.double 100,100,100,100,100,100,100,100,100,100

        # vector z
	# 60 elements are 480 bytes
z:      .space 480

        # scalar a
a:      .double 1

        # code
	.text

start:	
        addi t1,gp,y     # t1 points to y
        addi t2,gp,z     # t2 points to z
        addi t5,gp,x     # t5 points to x
        fld f0,a(gp)     # f0 holds a
        addi t3, t1, 480 # 60 elements are 480 bytes
loop:
        fld f5, 0(t5)
        fmul.d f2,f0,f5
        fld f1, 0(t1)
        fadd.d f2, f2, f1
        fsd f2, 0(t2)

        fld f10, 8(t5)
        fmul.d f6,f0,f10
        fld f7, 8(t1)
        fadd.d f6, f6, f7
        fsd f6, 8(t2)

        fld f15, 16(t5)
        fmul.d f8,f0,f15
        fld f9, 16(t1)
        fadd.d f8, f8, f9
        fsd f8, 16(t2)

        addi t1,t1,24
        addi t5,t5,24
        sub t4,t3,t1
        addi t2,t2,24
        bnez t4,loop

	ori a7,zero,10         # end
	ecall