	.file	"f_lanzamiento.c"
	.text
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"X"
.LC1:
	.string	"i"
.LC2:
	.string	"<i>I</i>"
.LC3:
	.string	"%s"
.LC4:
	.string	"f_lanzamiento.c"
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC5:
	.string	"ERROR Issue (%s:%d): Operaci\303\263n %s (%d) no implementada\n"
	.align 8
.LC6:
	.string	"ERROR Issue (%s:%d): Instrucci\303\263n %x (%s) no implementada\n"
	.section	.rodata.str1.1
.LC7:
	.string	"I"
	.text
	.p2align 4
	.globl	fase_ISS
	.type	fase_ISS, @function
fase_ISS:
.LFB39:
	.cfi_startproc
	endbr64
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	leaq	-632(%rsp), %rsp
	.cfi_def_cfa_offset 688
	movq	IF_ISS_2@GOTPCREL(%rip), %rbx
	movq	%fs:40, %rax
	movq	%rax, 616(%rsp)
	xorl	%eax, %eax
	movl	192(%rbx), %eax
	movq	136(%rbx), %rdi
	testl	%eax, %eax
	je	.L246
	movq	PC_ISS@GOTPCREL(%rip), %rax
	movq	%rdi, (%rax)
.L233:
	movl	$1, %eax
.L1:
	movq	616(%rsp), %rdx
	subq	%fs:40, %rdx
	jne	.L247
	leaq	632(%rsp), %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 56
	popq	%rbx
	.cfi_def_cfa_offset 48
	popq	%rbp
	.cfi_def_cfa_offset 40
	popq	%r12
	.cfi_def_cfa_offset 32
	popq	%r13
	.cfi_def_cfa_offset 24
	popq	%r14
	.cfi_def_cfa_offset 16
	popq	%r15
	.cfi_def_cfa_offset 8
	ret
	.p2align 4,,7
	.p2align 3
.L246:
	.cfi_restore_state
	movl	184(%rbx), %eax
	testl	%eax, %eax
	je	.L248
	movq	PC_ISS@GOTPCREL(%rip), %rax
	movl	$1001, %r14d
	movq	%rdi, (%rax)
.L7:
	movq	Cancelar_Activa@GOTPCREL(%rip), %rax
	movq	presentacion@GOTPCREL(%rip), %rdx
	movl	144(%rbx), %esi
	movl	(%rax), %eax
	movq	72(%rdx), %rcx
	orl	188(%rbx), %eax
	jne	.L249
	movq	ISSstall@GOTPCREL(%rip), %rbp
	movl	0(%rbp), %r12d
	testl	%r12d, %r12d
	jne	.L250
	xorl	%edx, %edx
	leaq	.LC2(%rip), %rdi
	call	*%rcx
	movq	RB_long@GOTPCREL(%rip), %rax
	movq	config@GOTPCREL(%rip), %r13
	movl	$1, 0(%rbp)
	movl	(%rax), %eax
	cmpl	%eax, 36(%r13)
	jle	.L49
	movq	RB_fin@GOTPCREL(%rip), %r15
	movq	RB@GOTPCREL(%rip), %r9
	movl	184(%rbx), %ecx
	movslq	(%r15), %r12
	movdqu	152(%rbx), %xmm0
	movdqu	168(%rbx), %xmm1
	leaq	(%r12,%r12,2), %rax
	movq	%r12, %rdx
	leaq	(%r12,%rax,4), %r10
	movl	148(%rbx), %eax
	salq	$3, %r10
	leaq	(%r9,%r10), %rdi
	movl	%eax, 44(%rdi)
	movq	136(%rbx), %rax
	movl	%ecx, 80(%rdi)
	movdqu	%xmm0, 48(%rdi)
	movdqu	%xmm1, 64(%rdi)
	cmpq	$-1, %rax
	je	.L233
	cmpl	$1001, %r14d
	je	.L251
	movl	64(%rsp), %esi
	cmpl	$55, %esi
	jg	.L17
	cmpl	$2, %esi
	jle	.L19
	subl	$3, %esi
	cmpl	$52, %esi
	ja	.L19
	leaq	.L34(%rip), %rdi
	movslq	(%rdi,%rsi,4), %rcx
	addq	%rdi, %rcx
	notrack jmp	*%rcx
	.section	.rodata
	.align 4
	.align 4
.L34:
	.long	.L44-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L43-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L42-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L41-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L39-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L40-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L39-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L38-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L37-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L36-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L35-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L32-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L19-.L34
	.long	.L33-.L34
	.text
	.p2align 4,,7
	.p2align 3
.L248:
	leaq	352(%rsp), %rax
	leaq	64(%rsp), %rcx
	movq	%rax, 40(%rsp)
	leaq	96(%rsp), %rax
	movq	%rax, 32(%rsp)
	leaq	88(%rsp), %rax
	movq	%rax, 24(%rsp)
	leaq	84(%rsp), %rax
	movq	%rax, 16(%rsp)
	leaq	80(%rsp), %rax
	movq	%rax, 8(%rsp)
	leaq	76(%rsp), %rax
	movq	%rax, (%rsp)
	leaq	92(%rsp), %rdx
	movl	(%rbx), %esi
	leaq	72(%rsp), %r9
	leaq	68(%rsp), %r8
	call	fn_riscv_decode@PLT
	cmpl	$58, %eax
	movl	%eax, %r14d
	jne	.L252
.L4:
	xorl	%eax, %eax
	call	fase_ISS_alum@PLT
	jmp	.L1
	.p2align 4,,7
	.p2align 3
.L252:
	cmpl	$84, %eax
	je	.L4
	leal	-51(%rax), %ecx
	cmpl	$35, %ecx
	.p2align 4,,2
	ja	.L5
	movabsq	$58049168225, %rax
	shrq	%cl, %rax
	testb	$1, %al
	jne	.L4
.L5:
	movl	192(%rbx), %r15d
	movq	136(%rbx), %rdx
	movq	PC_ISS@GOTPCREL(%rip), %rax
	testl	%r15d, %r15d
	movq	%rdx, (%rax)
	je	.L7
	jmp	.L233
	.p2align 4,,7
	.p2align 3
.L250:
	xorl	%edx, %edx
	leaq	.LC1(%rip), %rdi
	movl	%eax, 48(%rsp)
	call	*%rcx
	movl	48(%rsp), %eax
	jmp	.L1
	.p2align 4,,7
	.p2align 3
.L249:
	movl	184(%rbx), %r13d
	testl	%r13d, %r13d
	jne	.L10
	xorl	%edx, %edx
	leaq	.LC0(%rip), %rdi
	call	*%rcx
	jmp	.L233
	.p2align 4,,7
	.p2align 3
.L10:
	movl	$1, %edx
	leaq	.LC0(%rip), %rdi
	call	*%rcx
	movl	$1, %eax
	jmp	.L1
	.p2align 4,,7
	.p2align 3
.L251:
	movl	144(%rbx), %esi
	testl	%ecx, %ecx
	movq	%rax, 88(%rdi)
	movabsq	$4299262263297, %rax
	movl	$0, 40(%rdi)
	movl	%esi, 100(%rdi)
	movq	%rax, (%r9,%r10)
	movl	$1, 8(%rdi)
	je	.L163
	cmpl	$32, %ecx
	jg	.L163
.L14:
	movq	Excepcion_Activa@GOTPCREL(%rip), %rax
	movl	$1, (%rax)
.L150:
	movq	presentacion@GOTPCREL(%rip), %rax
	movl	$1, %edx
	leaq	.LC7(%rip), %rdi
	movl	$1, %ebx
	call	*72(%rax)
.L151:
	movl	(%r15), %eax
	movl	%ebx, 0(%rbp)
	addl	$1, %eax
	movl	%eax, %edx
	sarl	$31, %edx
	idivl	36(%r13)
	movq	RB_long@GOTPCREL(%rip), %rax
	addl	$1, (%rax)
	movl	$1, %eax
	movl	%edx, (%r15)
	jmp	.L1
	.p2align 4,,7
	.p2align 3
.L271:
	cmpl	$58, %esi
	jle	.L19
	leal	-59(%rsi), %ecx
	movl	$1, %edi
	salq	%cl, %rdi
	testl	$1118464, %edi
	movq	%rdi, %rcx
	jne	.L30
	cmpl	$83, %esi
	je	.L31
	andl	$1, %ecx
	jne	.L32
.L19:
	leaq	352(%rsp), %rax
	movl	$900, %r8d
	movq	%rax, (%rsp)
	leaq	.LC4(%rip), %rcx
	movq	stderr@GOTPCREL(%rip), %rax
	leaq	.LC6(%rip), %rdx
	movl	(%rbx), %r9d
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	$1, %edi
	call	exit@PLT
	.p2align 4,,7
	.p2align 3
.L39:
	movl	16(%r13), %edi
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L68
	.p2align 4,,7
	.p2align 3
.L253:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%ecx, %edi
	je	.L49
.L68:
	movl	(%rsi), %r11d
	testl	%r11d, %r11d
	jne	.L253
	movslq	%ecx, %rcx
	movslq	%r14d, %r8
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rdi
	movl	144(%rbx), %esi
	movl	$1, (%rdi)
	movl	$0, 204(%rdi)
	movl	%r14d, 4(%rdi)
	movl	%edx, 228(%rdi)
	movl	%esi, 244(%rdi)
	leaq	(%r12,%r12,2), %rdi
	leaq	(%r12,%rdi,4), %rdi
	leaq	(%r9,%rdi,8), %r11
	movq	%rax, 88(%r11)
	leaq	(%r8,%r8,2), %rax
	movl	$1, (%r11)
	salq	$4, %rax
	movl	%r14d, 4(%r11)
	addq	instruction_list@GOTPCREL(%rip), %rax
	movl	%esi, 100(%r11)
	movl	$0, 8(%r11)
	movl	40(%rax), %eax
	movslq	80(%rsp), %rdi
	salq	$4, %rax
	addq	inst_types_list@GOTPCREL(%rip), %rax
	movq	%rdi, %rbx
	movq	%rdi, 16(%r11)
	movl	4(%rax), %eax
	leal	-4(%rax), %r8d
	cmpl	$1, %r8d
	jbe	.L254
	subl	$6, %eax
	andl	$-3, %eax
	jne	.L71
	movslq	68(%rsp), %r8
	salq	$4, %r8
	addq	Rfp@GOTPCREL(%rip), %r8
	movl	8(%r8), %eax
	cmpl	$4096, %eax
	je	.L215
.L72:
	movslq	%eax, %r8
	imulq	$104, %r8, %r8
	addq	%r9, %r8
	cmpl	$0, 8(%r8)
	je	.L70
	imulq	$248, %rcx, %rax
	movq	24(%r8), %r8
	movq	%r8, 16(%r10,%rax)
	movl	$4096, %eax
.L70:
	movq	%rcx, %r8
	salq	$5, %r8
	subq	%rcx, %r8
	testl	%ebx, %ebx
	leaq	(%r10,%r8,8), %rcx
	movl	%eax, 8(%rcx)
	movslq	88(%rsp), %rax
	movl	$4096, 24(%rcx)
	movq	%rax, 32(%rcx)
	je	.L62
	movq	%rdi, %rax
	salq	$4, %rax
	addq	Rint@GOTPCREL(%rip), %rax
	movl	%edx, 8(%rax)
	.p2align 4
	.p2align 3
.L62:
	movq	Excepcion_Activa@GOTPCREL(%rip), %rax
	movl	(%rax), %ebx
	movq	presentacion@GOTPCREL(%rip), %rax
	testl	%ebx, %ebx
	movq	72(%rax), %rax
	jne	.L150
	xorl	%edx, %edx
	leaq	.LC7(%rip), %rdi
	call	*%rax
	jmp	.L151
.L40:
	movl	16(%r13), %edi
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L74
	.p2align 4,,7
	.p2align 3
.L255:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%ecx, %edi
	je	.L49
.L74:
	movl	(%rsi), %r8d
	testl	%r8d, %r8d
	jne	.L255
	movslq	%ecx, %rcx
	leaq	(%r12,%r12,2), %rdi
	movq	%rcx, %rsi
	leaq	(%r12,%rdi,4), %rdi
	movslq	80(%rsp), %r8
	leaq	(%r9,%rdi,8), %rdi
	salq	$5, %rsi
	movl	$1, (%rdi)
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movq	%r8, %r11
	movl	$1, (%rsi)
	movl	%r14d, 4(%rsi)
	movl	%r14d, 4(%rdi)
	movq	%r8, 16(%rdi)
	movl	$0, 8(%rdi)
	movl	%edx, 228(%rsi)
	movq	%rax, 16(%rsi)
.L244:
	movl	$4096, 8(%rsi)
	testl	%r11d, %r11d
	movslq	88(%rsp), %rdi
	movl	$4096, 24(%rsi)
	movq	%rdi, 32(%rsi)
	je	.L146
	movq	%r8, %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	%edx, 8(%rsi)
.L146:
	movq	%rcx, %rdx
	salq	$5, %rdx
	subq	%rcx, %rdx
	leaq	(%r10,%rdx,8), %rdx
	movl	$0, 204(%rdx)
	movl	144(%rbx), %esi
	movl	%esi, 244(%rdx)
.L230:
	leaq	(%r12,%r12,2), %rdx
	leaq	(%r12,%rdx,4), %rdx
	leaq	(%r9,%rdx,8), %rdx
	movl	%esi, 100(%rdx)
	movq	%rax, 88(%rdx)
	jmp	.L62
.L43:
	movl	20(%r13), %ecx
	movl	28(%r13), %esi
	addl	16(%r13), %ecx
	addl	24(%r13), %ecx
	addl	%ecx, %esi
	cmpl	%esi, %ecx
	jge	.L49
	movq	RS@GOTPCREL(%rip), %r10
	movslq	%ecx, %rdi
	movq	%rdi, %rax
	salq	$5, %rax
	subq	%rdi, %rax
	leaq	(%r10,%rax,8), %rax
	jmp	.L64
	.p2align 4,,7
	.p2align 3
.L256:
	addl	$1, %ecx
	addq	$248, %rax
	cmpl	%esi, %ecx
	je	.L49
.L64:
	movl	(%rax), %r8d
	testl	%r8d, %r8d
	jne	.L256
	testl	%ecx, %ecx
	js	.L49
	movslq	%ecx, %rcx
	movq	%rcx, %rax
	salq	$5, %rax
	subq	%rcx, %rax
	leaq	(%r10,%rax,8), %rax
	movl	$1, (%rax)
	movl	%r14d, 4(%rax)
	movl	%edx, 228(%rax)
	movslq	68(%rsp), %rax
	salq	$4, %rax
	addq	Rint@GOTPCREL(%rip), %rax
	movl	8(%rax), %esi
	cmpl	$4096, %esi
	je	.L257
	movslq	%esi, %rax
	leaq	(%rax,%rax,2), %rdi
	leaq	(%rax,%rdi,4), %rdi
	movl	8(%r9,%rdi,8), %edi
	testl	%edi, %edi
	je	.L66
	imulq	$104, %rax, %rax
	imulq	$248, %rcx, %rsi
	movq	24(%r9,%rax), %rax
	movq	%rax, 16(%r10,%rsi)
	movl	$4096, %esi
.L66:
	movq	%rcx, %rax
	salq	$5, %rax
	subq	%rcx, %rax
	movslq	88(%rsp), %rcx
	salq	$3, %rax
	leaq	(%r10,%rax), %r11
	leaq	72(%r10,%rax), %rdi
	movq	%rcx, 64(%r11)
	leaq	(%r12,%r12,2), %rcx
	movl	%esi, 8(%r11)
	leaq	(%r12,%rcx,4), %rcx
	movq	%r11, 48(%rsp)
	leaq	(%r9,%rcx,8), %r12
	movslq	80(%rsp), %rcx
	movl	$1, (%r12)
	movl	%r14d, 4(%r12)
	movq	%rcx, 16(%r12)
	salq	$4, %rcx
	movl	$0, 8(%r12)
	addq	Rfp@GOTPCREL(%rip), %rcx
	movl	%edx, 8(%rcx)
.L232:
	movl	$1, %esi
	leaq	4(%rbx), %r8
	leaq	.LC3(%rip), %rcx
	movl	$128, %edx
	xorl	%eax, %eax
	call	__sprintf_chk@PLT
	movq	48(%rsp), %r11
	movq	136(%rbx), %rax
	movl	$0, 204(%r11)
	movl	144(%rbx), %esi
	movl	%esi, 244(%r11)
	movl	%esi, 100(%r12)
	movq	%rax, 88(%r12)
	jmp	.L62
.L44:
	movl	20(%r13), %eax
	movl	28(%r13), %esi
	addl	16(%r13), %eax
	addl	24(%r13), %eax
	addl	%eax, %esi
	cmpl	%esi, %eax
	jge	.L49
	movq	RS@GOTPCREL(%rip), %r10
	movslq	%eax, %rdi
	movq	%rdi, %rcx
	salq	$5, %rcx
	subq	%rdi, %rcx
	leaq	(%r10,%rcx,8), %rcx
	jmp	.L55
	.p2align 4,,7
	.p2align 3
.L258:
	addl	$1, %eax
	addq	$248, %rcx
	cmpl	%esi, %eax
	je	.L49
.L55:
	movl	(%rcx), %r11d
	testl	%r11d, %r11d
	jne	.L258
	testl	%eax, %eax
	js	.L49
	cltq
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	leaq	(%r10,%rcx,8), %rcx
	movl	%r14d, 4(%rcx)
	movl	$1, (%rcx)
	movl	%edx, 228(%rcx)
	movslq	%r14d, %rcx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	addq	instruction_list@GOTPCREL(%rip), %rcx
	movl	40(%rcx), %ecx
	salq	$4, %rcx
	addq	inst_types_list@GOTPCREL(%rip), %rcx
	movl	4(%rcx), %ecx
	leal	-4(%rcx), %esi
	cmpl	$1, %esi
	jbe	.L259
	subl	$6, %ecx
	andl	$-3, %ecx
	jne	.L59
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %ecx
	cmpl	$4096, %ecx
	je	.L213
.L60:
	movslq	%ecx, %rsi
	imulq	$104, %rsi, %rsi
	addq	%r9, %rsi
	cmpl	$0, 8(%rsi)
	je	.L58
	imulq	$248, %rax, %rcx
	movq	24(%rsi), %rsi
	movq	%rsi, 16(%r10,%rcx)
	movl	$4096, %ecx
.L58:
	movq	%rax, %rsi
	salq	$5, %rsi
	subq	%rax, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%ecx, 8(%rsi)
	movslq	88(%rsp), %rcx
	movq	$0, 32(%rsi)
	movl	$4096, 24(%rsi)
	movq	%rcx, 64(%rsi)
	leaq	(%r12,%r12,2), %rcx
	leaq	(%r12,%rcx,4), %rcx
	leaq	(%r9,%rcx,8), %rsi
	movslq	80(%rsp), %rcx
	movl	$1, (%rsi)
	movl	%r14d, 4(%rsi)
	movq	%rcx, 16(%rsi)
	testl	%ecx, %ecx
	movl	$0, 8(%rsi)
	je	.L61
	salq	$4, %rcx
	addq	Rint@GOTPCREL(%rip), %rcx
	movl	%edx, 8(%rcx)
.L61:
	movq	%rax, %r11
	movl	$1, %esi
	salq	$5, %r11
	leaq	4(%rbx), %r8
	subq	%rax, %r11
	leaq	.LC3(%rip), %rcx
	leaq	0(,%r11,8), %r14
	movl	$128, %edx
	xorl	%eax, %eax
	leaq	72(%r10,%r14), %rdi
	movq	%r10, 48(%rsp)
	call	__sprintf_chk@PLT
	movq	48(%rsp), %r10
	movl	144(%rbx), %esi
	leaq	(%r10,%r14), %r11
	movl	$0, 204(%r11)
	movl	%esi, 244(%r11)
.L231:
	movq	RB@GOTPCREL(%rip), %r9
	leaq	(%r12,%r12,2), %rax
	movq	136(%rbx), %rdx
	leaq	(%r12,%rax,4), %rax
	leaq	(%r9,%rax,8), %rax
	movl	%esi, 100(%rax)
	movq	%rdx, 88(%rax)
	jmp	.L62
.L33:
	movl	16(%r13), %edi
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L99
	.p2align 4,,7
	.p2align 3
.L260:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%edi, %ecx
	je	.L49
.L99:
	movl	(%rsi), %r8d
	testl	%r8d, %r8d
	jne	.L260
	movslq	%ecx, %rcx
	leaq	(%r12,%r12,2), %rdi
	movq	%rcx, %rsi
	leaq	(%r12,%rdi,4), %rdi
	movslq	80(%rsp), %r8
	leaq	(%r9,%rdi,8), %rdi
	salq	$5, %rsi
	movl	$1, (%rdi)
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movq	%r8, %r11
	movl	$1, (%rsi)
	movl	%r14d, 4(%rsi)
	movl	%r14d, 4(%rdi)
	movq	%r8, 16(%rdi)
	movl	$0, 8(%rdi)
	movl	%edx, 228(%rsi)
	movq	$0, 16(%rsi)
	jmp	.L244
.L32:
	movl	16(%r13), %edi
	movslq	%r14d, %rcx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	addq	instruction_list@GOTPCREL(%rip), %rcx
	movl	(%rcx), %ecx
	cmpl	$1, %ecx
	je	.L87
	cmpl	$6, %ecx
	je	.L87
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L92
.L261:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%edi, %ecx
	je	.L49
.L92:
	movl	(%rsi), %r11d
	testl	%r11d, %r11d
	jne	.L261
.L91:
	movslq	68(%rsp), %rdi
	movslq	%ecx, %rcx
	movq	%rcx, %rsi
	salq	$5, %rsi
	salq	$4, %rdi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%r14d, 4(%rsi)
	movl	$1, (%rsi)
	movl	%edx, 228(%rsi)
	leaq	(%r12,%r12,2), %rsi
	leaq	(%r12,%rsi,4), %rsi
	leaq	(%r9,%rsi,8), %rsi
	movl	%r14d, 4(%rsi)
	movslq	80(%rsp), %r14
	movl	$1, (%rsi)
	movl	$0, 8(%rsi)
	movq	%r14, 16(%rsi)
	movq	Rint@GOTPCREL(%rip), %rsi
	movl	%r14d, 48(%rsp)
	addq	%rsi, %rdi
	movl	8(%rdi), %r11d
	cmpl	$4096, %r11d
	je	.L262
	movslq	%r11d, %rdi
	leaq	(%rdi,%rdi,2), %r8
	leaq	(%rdi,%r8,4), %r8
	movl	8(%r9,%r8,8), %r8d
	testl	%r8d, %r8d
	je	.L94
	imulq	$104, %rdi, %rdi
	movl	$4096, %r11d
	imulq	$248, %rcx, %r8
	movq	24(%r9,%rdi), %rdi
	movq	%rdi, 16(%r10,%r8)
.L94:
	movq	%rcx, %rdi
	salq	$5, %rdi
	subq	%rcx, %rdi
	movl	%r11d, 8(%r10,%rdi,8)
	movslq	72(%rsp), %rdi
	salq	$4, %rdi
	addq	%rsi, %rdi
	movl	8(%rdi), %r11d
	cmpl	$4096, %r11d
	je	.L263
	movslq	%r11d, %rdi
	leaq	(%rdi,%rdi,2), %r8
	leaq	(%rdi,%r8,4), %r8
	movl	8(%r9,%r8,8), %r8d
	testl	%r8d, %r8d
	je	.L96
	imulq	$104, %rdi, %rdi
	movl	$4096, %r11d
	imulq	$248, %rcx, %r8
	movq	24(%r9,%rdi), %rdi
	movq	%rdi, 32(%r10,%r8)
.L96:
	movq	%rcx, %rdi
	salq	$5, %rdi
	subq	%rcx, %rdi
	movl	%r11d, 24(%r10,%rdi,8)
	movl	48(%rsp), %r11d
	testl	%r11d, %r11d
	je	.L146
	movq	%r14, %rdi
	salq	$4, %rdi
	movl	%edx, 8(%rsi,%rdi)
	jmp	.L146
.L35:
	movslq	%r14d, %rax
	movl	$1, %esi
	movq	instruction_list@GOTPCREL(%rip), %rdx
	leaq	(%rax,%rax,2), %rax
	movl	%r14d, (%rsp)
	movl	$534, %r8d
	salq	$4, %rax
	leaq	.LC4(%rip), %rcx
	leaq	4(%rax,%rdx), %r9
	leaq	.LC5(%rip), %rdx
	movq	stderr@GOTPCREL(%rip), %rax
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	144(%rbx), %esi
	jmp	.L62
.L37:
	movl	20(%r13), %eax
	movl	32(%r13), %esi
	addl	16(%r13), %eax
	addl	24(%r13), %eax
	addl	28(%r13), %eax
	addl	%eax, %esi
	cmpl	%esi, %eax
	jge	.L49
	movq	RS@GOTPCREL(%rip), %r10
	movslq	%eax, %rdi
	movq	%rdi, %rcx
	salq	$5, %rcx
	subq	%rdi, %rcx
	leaq	(%r10,%rcx,8), %rcx
	jmp	.L82
	.p2align 4,,7
	.p2align 3
.L264:
	addl	$1, %eax
	addq	$248, %rcx
	cmpl	%esi, %eax
	je	.L49
.L82:
	movl	(%rcx), %r8d
	testl	%r8d, %r8d
	jne	.L264
	testl	%eax, %eax
	js	.L49
	cltq
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	leaq	(%r10,%rcx,8), %rcx
	movl	$1, (%rcx)
	movl	%r14d, 4(%rcx)
	movslq	68(%rsp), %rcx
	salq	$4, %rcx
	addq	Rint@GOTPCREL(%rip), %rcx
	movl	8(%rcx), %esi
	cmpl	$4096, %esi
	je	.L265
	movslq	%esi, %rcx
	leaq	(%rcx,%rcx,2), %rdi
	leaq	(%rcx,%rdi,4), %rdi
	movl	8(%r9,%rdi,8), %edi
	testl	%edi, %edi
	je	.L84
	imulq	$104, %rcx, %rcx
	imulq	$248, %rax, %rsi
	movq	24(%r9,%rcx), %rcx
	movq	%rcx, 16(%r10,%rsi)
	movl	$4096, %esi
.L84:
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	movl	%esi, 8(%r10,%rcx,8)
	movslq	72(%rsp), %rcx
	salq	$4, %rcx
	addq	Rfp@GOTPCREL(%rip), %rcx
	movl	8(%rcx), %esi
	cmpl	$4096, %esi
	je	.L266
	movslq	%esi, %rcx
	leaq	(%rcx,%rcx,2), %rdi
	leaq	(%rcx,%rdi,4), %rdi
	movl	8(%r9,%rdi,8), %r11d
	testl	%r11d, %r11d
	je	.L86
	imulq	$104, %rcx, %rcx
	imulq	$248, %rax, %rsi
	movq	24(%r9,%rcx), %rcx
	movq	%rcx, 32(%r10,%rsi)
	movl	$4096, %esi
.L86:
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	salq	$3, %rcx
	leaq	(%r10,%rcx), %r11
	leaq	72(%r10,%rcx), %rdi
	movl	%esi, 24(%r11)
	movslq	88(%rsp), %rsi
	movl	$0, 200(%r11)
	movl	%edx, 228(%r11)
	movq	%rsi, 64(%r11)
	leaq	(%r12,%r12,2), %rsi
	movq	%r11, 48(%rsp)
	leaq	(%r12,%rsi,4), %rsi
	leaq	(%r9,%rsi,8), %r12
	movl	$1, (%r12)
	movl	%r14d, 4(%r12)
	movq	%rax, 16(%r12)
	movl	$0, 8(%r12)
	jmp	.L232
.L38:
	movl	20(%r13), %eax
	movl	32(%r13), %esi
	addl	16(%r13), %eax
	addl	24(%r13), %eax
	addl	28(%r13), %eax
	addl	%eax, %esi
	cmpl	%eax, %esi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	movslq	%eax, %rdi
	movq	%rdi, %rcx
	salq	$5, %rcx
	subq	%rdi, %rcx
	leaq	(%r10,%rcx,8), %rcx
	jmp	.L76
	.p2align 4,,7
	.p2align 3
.L267:
	addl	$1, %eax
	addq	$248, %rcx
	cmpl	%eax, %esi
	je	.L49
.L76:
	movl	(%rcx), %edi
	testl	%edi, %edi
	jne	.L267
	testl	%eax, %eax
	js	.L49
	movq	Rint@GOTPCREL(%rip), %rsi
	cltq
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	leaq	(%r10,%rcx,8), %rcx
	movl	%edx, 228(%rcx)
	leaq	(%r12,%r12,2), %rdx
	movl	$1, (%rcx)
	leaq	(%r12,%rdx,4), %rdx
	movl	%r14d, 4(%rcx)
	leaq	(%r9,%rdx,8), %rdx
	movl	$0, 8(%rdx)
	movl	$1, (%rdx)
	movl	%r14d, 4(%rdx)
	movq	%rax, 16(%rdx)
	movslq	68(%rsp), %rdx
	salq	$4, %rdx
	addq	%rsi, %rdx
	movl	8(%rdx), %ecx
	cmpl	$4096, %ecx
	je	.L268
	movslq	%ecx, %rdx
	leaq	(%rdx,%rdx,2), %rdi
	leaq	(%rdx,%rdi,4), %rdi
	movl	8(%r9,%rdi,8), %r14d
	testl	%r14d, %r14d
	je	.L78
	imulq	$104, %rdx, %rdx
	imulq	$248, %rax, %rcx
	movq	24(%r9,%rdx), %rdx
	movq	%rdx, 16(%r10,%rcx)
	movl	$4096, %ecx
.L78:
	movq	%rax, %rdx
	salq	$5, %rdx
	subq	%rax, %rdx
	movl	%ecx, 8(%r10,%rdx,8)
	movslq	72(%rsp), %rdx
	salq	$4, %rdx
	addq	%rsi, %rdx
	movl	8(%rdx), %ecx
	cmpl	$4096, %ecx
	je	.L269
	movslq	%ecx, %rdx
	leaq	(%rdx,%rdx,2), %rsi
	leaq	(%rdx,%rsi,4), %rsi
	movl	8(%r9,%rsi,8), %r11d
	testl	%r11d, %r11d
	je	.L80
	imulq	$104, %rdx, %rdx
	imulq	$248, %rax, %rcx
	movq	24(%r9,%rdx), %rdx
	movq	%rdx, 32(%r10,%rcx)
	movl	$4096, %ecx
.L80:
	movq	%rax, %rdx
	movl	$1, %esi
	salq	$5, %rdx
	leaq	4(%rbx), %r8
	subq	%rax, %rdx
	leaq	0(,%rdx,8), %rax
	movslq	88(%rsp), %rdx
	leaq	(%r10,%rax), %r14
	movl	%ecx, 24(%r14)
	leaq	72(%r10,%rax), %rdi
	movl	$0, 200(%r14)
	leaq	.LC3(%rip), %rcx
	movq	%rdx, 64(%r14)
	xorl	%eax, %eax
	movl	$128, %edx
	call	__sprintf_chk@PLT
	movl	144(%rbx), %esi
	movl	$0, 204(%r14)
	movl	%esi, 244(%r14)
	jmp	.L231
.L36:
	movslq	%r14d, %rax
	movl	$528, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
.L234:
	movq	stderr@GOTPCREL(%rip), %rax
	leaq	.LC4(%rip), %rcx
	leaq	.LC5(%rip), %rdx
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	$1, %edi
	call	exit@PLT
.L41:
	movslq	%r14d, %rax
	movl	$341, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
.L42:
	movslq	%r14d, %rax
	movl	$335, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
	.p2align 4,,7
	.p2align 3
.L87:
	movl	20(%r13), %ecx
	addl	%edi, %ecx
	movl	24(%r13), %edi
	addl	%ecx, %edi
	cmpl	%ecx, %edi
	jle	.L49
	movslq	%ecx, %r10
	movq	%r10, %rsi
	salq	$5, %rsi
	subq	%r10, %rsi
	movq	RS@GOTPCREL(%rip), %r10
	leaq	(%r10,%rsi,8), %rsi
	jmp	.L90
.L270:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%ecx, %edi
	je	.L49
.L90:
	movl	(%rsi), %r8d
	testl	%r8d, %r8d
	jne	.L270
	testl	%ecx, %ecx
	jns	.L91
	.p2align 4
	.p2align 3
.L49:
	xorl	%eax, %eax
	jmp	.L1
	.p2align 4,,7
	.p2align 3
.L17:
	cmpl	$83, %esi
	jle	.L271
	subl	$87, %esi
	cmpl	$36, %esi
	ja	.L19
	leaq	.L21(%rip), %rdi
	movslq	(%rdi,%rsi,4), %rsi
	addq	%rdi, %rsi
	notrack jmp	*%rsi
	.section	.rodata
	.align 4
	.align 4
.L21:
	.long	.L29-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L28-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L27-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L26-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L25-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L24-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L23-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L22-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L19-.L21
	.long	.L20-.L21
	.text
.L23:
	leaq	(%r12,%r12,2), %rdx
	testl	%ecx, %ecx
	leaq	(%r12,%rdx,4), %rdx
	leaq	(%r9,%rdx,8), %rdx
	movl	$1, (%rdx)
	movl	%r14d, 4(%rdx)
	movl	$0, 40(%rdx)
	movl	$1, 8(%rdx)
	je	.L164
	cmpl	$32, %ecx
	jg	.L164
.L148:
	movq	Excepcion_Activa@GOTPCREL(%rip), %rdx
	movl	144(%rbx), %esi
	movl	$1, (%rdx)
	leaq	(%r12,%r12,2), %rdx
	leaq	(%r12,%rdx,4), %rdx
	leaq	(%r9,%rdx,8), %rdx
	movl	%esi, 100(%rdx)
	movq	%rax, 88(%rdx)
	jmp	.L150
.L24:
	movslq	88(%rsp), %rdi
	leaq	(%r12,%r12,2), %rcx
	movslq	80(%rsp), %rsi
	leaq	(%r12,%rcx,4), %rcx
	leaq	(%r9,%rcx,8), %rcx
	movl	$1, (%rcx)
	addq	%rax, %rdi
	movq	%rdi, 32(%rcx)
	testl	%esi, %esi
	movl	%r14d, 4(%rcx)
	leaq	4(%rax), %rdi
	movq	%rsi, 16(%rcx)
	movl	$1, 40(%rcx)
	movl	$1, 8(%rcx)
	movq	%rdi, 24(%rcx)
	je	.L147
	movq	%rsi, %rcx
	salq	$4, %rcx
	addq	Rint@GOTPCREL(%rip), %rcx
	movl	%edx, 8(%rcx)
.L147:
	movl	144(%rbx), %esi
	jmp	.L230
.L26:
	movl	16(%r13), %edi
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L141
	.p2align 4,,7
	.p2align 3
.L272:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%edi, %ecx
	je	.L49
.L141:
	movl	(%rsi), %r8d
	testl	%r8d, %r8d
	jne	.L272
	movslq	%ecx, %rcx
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%r14d, 4(%rsi)
	movl	$1, (%rsi)
	movl	%edx, 228(%rsi)
	movslq	%r14d, %rsi
	leaq	(%rsi,%rsi,2), %rsi
	salq	$4, %rsi
	addq	instruction_list@GOTPCREL(%rip), %rsi
	movl	40(%rsi), %esi
	salq	$4, %rsi
	addq	inst_types_list@GOTPCREL(%rip), %rsi
	movl	4(%rsi), %esi
	leal	-4(%rsi), %edi
	cmpl	$1, %edi
	jbe	.L273
	subl	$6, %esi
	andl	$-3, %esi
	jne	.L144
	movslq	68(%rsp), %rdi
	salq	$4, %rdi
	addq	Rfp@GOTPCREL(%rip), %rdi
	movl	8(%rdi), %esi
	cmpl	$4096, %esi
	je	.L228
.L145:
	movslq	%esi, %rdi
	imulq	$104, %rdi, %rdi
	addq	%r9, %rdi
	cmpl	$0, 8(%rdi)
	je	.L143
	imulq	$248, %rcx, %rsi
	movq	24(%rdi), %rdi
	movq	%rdi, 16(%r10,%rsi)
	movl	$4096, %esi
.L143:
	movq	%rcx, %rdi
	leaq	4(%rax), %r11
	salq	$5, %rdi
	subq	%rcx, %rdi
	leaq	(%r10,%rdi,8), %rdi
	movl	%esi, 8(%rdi)
	movslq	88(%rsp), %rsi
	movl	$4096, 24(%rdi)
	movq	$0, 32(%rdi)
	movq	%rsi, 64(%rdi)
	leaq	(%r12,%r12,2), %rsi
	leaq	(%r12,%rsi,4), %rsi
	leaq	(%r9,%rsi,8), %rdi
	movslq	80(%rsp), %rsi
	movl	$1, (%rdi)
	movl	%r14d, 4(%rdi)
	movq	%rsi, 16(%rdi)
	testl	%esi, %esi
	movl	$0, 8(%rdi)
	movl	$1, 40(%rdi)
	movq	%r11, 24(%rdi)
	je	.L146
.L243:
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	%edx, 8(%rsi)
	jmp	.L146
.L27:
	movl	16(%r13), %edi
	testl	%edi, %edi
	jle	.L49
	movq	RS@GOTPCREL(%rip), %r10
	xorl	%ecx, %ecx
	movq	%r10, %rsi
	jmp	.L130
	.p2align 4,,7
	.p2align 3
.L274:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%edi, %ecx
	je	.L49
.L130:
	movl	(%rsi), %r11d
	testl	%r11d, %r11d
	jne	.L274
	movslq	%ecx, %rcx
	movslq	%r14d, %r8
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%edx, 228(%rsi)
	leaq	(%r12,%r12,2), %rdx
	movl	$1, (%rsi)
	leaq	(%r12,%rdx,4), %rdx
	movl	%r14d, 4(%rsi)
	leaq	(%r9,%rdx,8), %rdx
	movslq	88(%rsp), %rsi
	movl	$1, (%rdx)
	movl	%r14d, 4(%rdx)
	movl	$0, 8(%rdx)
	addq	%rax, %rsi
	movq	%rsi, 32(%rdx)
	leaq	(%r8,%r8,2), %rdx
	movq	inst_types_list@GOTPCREL(%rip), %r8
	salq	$4, %rdx
	addq	instruction_list@GOTPCREL(%rip), %rdx
	movl	40(%rdx), %edi
	movq	%rdi, %rdx
	salq	$4, %rdx
	movl	4(%r8,%rdx), %edx
	leal	-4(%rdx), %esi
	cmpl	$1, %esi
	jbe	.L275
	subl	$6, %edx
	andl	$-3, %edx
	jne	.L133
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %edx
	cmpl	$4096, %edx
	je	.L224
.L134:
	movslq	%edx, %rsi
	imulq	$104, %rsi, %rsi
	addq	%r9, %rsi
	cmpl	$0, 8(%rsi)
	je	.L132
	imulq	$248, %rcx, %rdx
	movq	24(%rsi), %rsi
	movq	%rsi, 16(%r10,%rdx)
	movl	$4096, %edx
.L132:
	salq	$4, %rdi
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	movl	%edx, 8(%r10,%rsi,8)
	movl	8(%r8,%rdi), %edx
	leal	-4(%rdx), %esi
	cmpl	$1, %esi
	jbe	.L276
	subl	$6, %edx
	andl	$-3, %edx
	jne	.L138
	movslq	72(%rsp), %rsi
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %edx
	cmpl	$4096, %edx
	je	.L226
.L139:
	movslq	%edx, %rsi
	imulq	$104, %rsi, %rsi
	addq	%r9, %rsi
	cmpl	$0, 8(%rsi)
	je	.L137
	imulq	$248, %rcx, %rdx
	movq	24(%rsi), %rsi
	movq	%rsi, 32(%r10,%rdx)
	movl	$4096, %edx
.L137:
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rcx
	movl	144(%rbx), %esi
	movl	%edx, 24(%rcx)
	movl	$0, 204(%rcx)
	movl	%esi, 244(%rcx)
	jmp	.L230
.L25:
	movslq	%r14d, %rax
	movl	$808, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
.L20:
	movslq	%r14d, %rax
	movl	$893, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
.L22:
	movslq	%r14d, %rax
	movl	$887, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
.L28:
	movslq	%r14d, %rax
	movl	$725, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
.L29:
	movslq	%r14d, %rax
	movl	$720, %r8d
	imulq	$48, %rax, %rax
	movq	instruction_list@GOTPCREL(%rip), %rdx
	movl	%r14d, (%rsp)
	leaq	4(%rax,%rdx), %r9
	jmp	.L234
	.p2align 4,,7
	.p2align 3
.L163:
	leaq	(%r12,%r12,2), %rax
	leaq	(%r12,%rax,4), %rdx
	movl	88(%rsp), %eax
	addl	$32, %eax
	movl	%eax, 80(%r9,%rdx,8)
	jmp	.L14
.L31:
	movl	16(%r13), %ecx
	movl	20(%r13), %edi
	addl	%ecx, %edi
	cmpl	%edi, %ecx
	jge	.L49
	movslq	%ecx, %r10
	movq	%r10, %rsi
	salq	$5, %rsi
	subq	%r10, %rsi
	movq	RS@GOTPCREL(%rip), %r10
	leaq	(%r10,%rsi,8), %rsi
	jmp	.L116
	.p2align 4,,7
	.p2align 3
.L277:
	addl	$1, %ecx
	addq	$248, %rsi
	cmpl	%edi, %ecx
	je	.L49
.L116:
	movl	(%rsi), %r8d
	testl	%r8d, %r8d
	jne	.L277
	testl	%ecx, %ecx
	js	.L49
	movl	84(%rsp), %edi
	movslq	%ecx, %rcx
	movq	inst_types_list@GOTPCREL(%rip), %r8
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%edi, 236(%rsi)
	movl	$1, (%rsi)
	movl	%r14d, 4(%rsi)
	movl	%edx, 228(%rsi)
	movslq	%r14d, %rsi
	leaq	(%rsi,%rsi,2), %rsi
	salq	$4, %rsi
	addq	instruction_list@GOTPCREL(%rip), %rsi
	movl	40(%rsi), %esi
	movq	%rsi, 48(%rsp)
	salq	$4, %rsi
	movl	4(%r8,%rsi), %esi
	leal	-4(%rsi), %edi
	cmpl	$1, %edi
	jbe	.L278
	subl	$6, %esi
	andl	$-3, %esi
	jne	.L120
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %r11d
	cmpl	$4096, %r11d
	je	.L220
.L121:
	movslq	%r11d, %rsi
	imulq	$104, %rsi, %rsi
	addq	%r9, %rsi
	cmpl	$0, 8(%rsi)
	je	.L119
	imulq	$248, %rcx, %rdi
	movq	24(%rsi), %rsi
	movl	$4096, %r11d
	movq	%rsi, 16(%r10,%rdi)
.L119:
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	movl	%r11d, 8(%r10,%rsi,8)
	movq	48(%rsp), %rsi
	salq	$4, %rsi
	movl	8(%r8,%rsi), %esi
	leal	-4(%rsi), %edi
	cmpl	$1, %edi
	jbe	.L279
	subl	$6, %esi
	andl	$-3, %esi
	jne	.L125
	movslq	72(%rsp), %rsi
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %r11d
	cmpl	$4096, %r11d
	je	.L222
.L126:
	movslq	%r11d, %rsi
	imulq	$104, %rsi, %rsi
	addq	%r9, %rsi
	cmpl	$0, 8(%rsi)
	je	.L124
	imulq	$248, %rcx, %rdi
	movq	24(%rsi), %rsi
	movl	$4096, %r11d
	movq	%rsi, 32(%r10,%rdi)
.L124:
	movq	%rcx, %rsi
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	(%r10,%rsi,8), %rsi
	movl	%r11d, 24(%rsi)
	movl	$4096, 40(%rsi)
	movq	$0, 48(%rsi)
	leaq	(%r12,%r12,2), %rsi
	leaq	(%r12,%rsi,4), %rsi
	leaq	(%r9,%rsi,8), %rdi
	movslq	80(%rsp), %rsi
	movl	$1, (%rdi)
	movl	%r14d, 4(%rdi)
	movl	$0, 8(%rdi)
	movq	%rsi, 16(%rdi)
	movq	48(%rsp), %rdi
	salq	$4, %rdi
	movl	(%r8,%rdi), %edi
	subl	$6, %edi
	andl	$-3, %edi
	jne	.L127
	salq	$4, %rsi
	addq	Rfp@GOTPCREL(%rip), %rsi
	movl	%edx, 8(%rsi)
	jmp	.L146
.L30:
	movl	20(%r13), %esi
	movl	24(%r13), %edi
	addl	16(%r13), %esi
	addl	%esi, %edi
	cmpl	%edi, %esi
	jge	.L49
	movslq	%esi, %r10
	movq	%r10, %rcx
	salq	$5, %rcx
	subq	%r10, %rcx
	movq	RS@GOTPCREL(%rip), %r10
	leaq	(%r10,%rcx,8), %rcx
	jmp	.L101
	.p2align 4,,7
	.p2align 3
.L280:
	addl	$1, %esi
	addq	$248, %rcx
	cmpl	%edi, %esi
	je	.L49
.L101:
	movl	(%rcx), %r11d
	testl	%r11d, %r11d
	jne	.L280
	testl	%esi, %esi
	js	.L49
	movq	inst_types_list@GOTPCREL(%rip), %r8
	movslq	%esi, %r11
	movq	%r11, %rcx
	salq	$5, %rcx
	subq	%r11, %rcx
	leaq	(%r10,%rcx,8), %rcx
	movl	$1, (%rcx)
	movl	%r14d, 4(%rcx)
	movl	%edx, 228(%rcx)
	movslq	%r14d, %rcx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	addq	instruction_list@GOTPCREL(%rip), %rcx
	movl	40(%rcx), %ecx
	movq	%rcx, 56(%rsp)
	salq	$4, %rcx
	movl	4(%r8,%rcx), %ecx
	leal	-4(%rcx), %esi
	cmpl	$1, %esi
	jbe	.L281
	subl	$6, %ecx
	andl	$-3, %ecx
	jne	.L105
	movslq	68(%rsp), %rcx
	movq	Rfp@GOTPCREL(%rip), %rsi
	salq	$4, %rcx
	movq	%rsi, 48(%rsp)
	addq	%rsi, %rcx
	movl	8(%rcx), %edi
	cmpl	$4096, %edi
	je	.L282
	movslq	%edi, %rcx
	imulq	$104, %rcx, %rcx
	addq	%r9, %rcx
	cmpl	$0, 8(%rcx)
	je	.L104
	imulq	$248, %r11, %rsi
	movq	24(%rcx), %rcx
	movl	$4096, %edi
	movq	%rcx, 16(%r10,%rsi)
.L104:
	movq	%r11, %rcx
	salq	$5, %rcx
	subq	%r11, %rcx
	movl	%edi, 8(%r10,%rcx,8)
	movq	56(%rsp), %rcx
	salq	$4, %rcx
	movl	8(%r8,%rcx), %ecx
	leal	-4(%rcx), %esi
	cmpl	$1, %esi
	jbe	.L283
	subl	$6, %ecx
	andl	$-3, %ecx
	jne	.L110
	movslq	72(%rsp), %rcx
	salq	$4, %rcx
	addq	48(%rsp), %rcx
	movl	8(%rcx), %edi
	cmpl	$4096, %edi
	je	.L218
.L111:
	movslq	%edi, %rcx
	imulq	$104, %rcx, %rcx
	addq	%r9, %rcx
	cmpl	$0, 8(%rcx)
	je	.L109
	imulq	$248, %r11, %rsi
	movq	24(%rcx), %rcx
	movl	$4096, %edi
	movq	%rcx, 32(%r10,%rsi)
.L109:
	movq	%r11, %rcx
	salq	$5, %rcx
	subq	%r11, %rcx
	movl	%edi, 24(%r10,%rcx,8)
	movq	56(%rsp), %rcx
	salq	$4, %rcx
	movl	12(%r8,%rcx), %ecx
	subl	$6, %ecx
	andl	$-3, %ecx
	jne	.L112
	movslq	76(%rsp), %rcx
	salq	$4, %rcx
	addq	48(%rsp), %rcx
	movl	8(%rcx), %esi
	cmpl	$4096, %esi
	je	.L284
	movslq	%esi, %rcx
	imulq	$104, %rcx, %rcx
	addq	%r9, %rcx
	cmpl	$0, 8(%rcx)
	je	.L114
	imulq	$248, %r11, %rsi
	movq	24(%rcx), %rcx
	movq	%rcx, 48(%r10,%rsi)
	movl	$4096, %esi
.L114:
	movq	%r11, %rcx
	salq	$5, %rcx
	subq	%r11, %rcx
	movq	48(%rsp), %r11
	leaq	(%r10,%rcx,8), %rcx
	movl	%esi, 40(%rcx)
	leaq	(%r12,%r12,2), %rsi
	movl	$0, 204(%rcx)
	leaq	(%r12,%rsi,4), %rsi
	leaq	(%r9,%rsi,8), %rdi
	movslq	80(%rsp), %rsi
	movl	$1, (%rdi)
	movl	%r14d, 4(%rdi)
	movq	%rsi, 16(%rdi)
	salq	$4, %rsi
	movl	%edx, 8(%r11,%rsi)
	movl	144(%rbx), %esi
	movl	$0, 8(%rdi)
	movq	%rax, 88(%rdi)
	movl	%esi, 244(%rcx)
	movl	%esi, 100(%rdi)
	jmp	.L62
.L164:
	leaq	(%r12,%r12,2), %rdx
	leaq	(%r12,%rdx,4), %rdx
	movl	$255, 80(%r9,%rdx,8)
	jmp	.L148
.L127:
	testl	%esi, %esi
	je	.L146
	jmp	.L243
.L112:
	imulq	$248, %r11, %rcx
	movl	$4096, %esi
	movq	$0, 48(%r10,%rcx)
	jmp	.L114
.L279:
	movslq	72(%rsp), %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %r11d
	cmpl	$4096, %r11d
	jne	.L126
.L222:
	imulq	$248, %rcx, %rdi
	movq	(%rsi), %rsi
	movq	%rsi, 32(%r10,%rdi)
	jmp	.L124
.L278:
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %r11d
	cmpl	$4096, %r11d
	jne	.L121
.L220:
	imulq	$248, %rcx, %rdi
	movq	(%rsi), %rsi
	movq	%rsi, 16(%r10,%rdi)
	jmp	.L119
.L281:
	movslq	68(%rsp), %rcx
	salq	$4, %rcx
	addq	Rint@GOTPCREL(%rip), %rcx
	movl	8(%rcx), %edi
	cmpl	$4096, %edi
	je	.L285
	movslq	%edi, %rcx
	imulq	$104, %rcx, %rcx
	addq	%r9, %rcx
	cmpl	$0, 8(%rcx)
	je	.L217
	imulq	$248, %r11, %rsi
	movq	24(%rcx), %rcx
	movl	$4096, %edi
	movq	%rcx, 16(%r10,%rsi)
	movq	Rfp@GOTPCREL(%rip), %rcx
	movq	%rcx, 48(%rsp)
	jmp	.L104
.L254:
	movslq	68(%rsp), %r8
	salq	$4, %r8
	addq	Rint@GOTPCREL(%rip), %r8
	movl	8(%r8), %eax
	cmpl	$4096, %eax
	jne	.L72
.L215:
	imulq	$248, %rcx, %r9
	movq	(%r8), %r8
	movq	%r8, 16(%r10,%r9)
	jmp	.L70
.L276:
	movslq	72(%rsp), %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %edx
	cmpl	$4096, %edx
	jne	.L139
.L226:
	imulq	$248, %rcx, %rdi
	movq	(%rsi), %rsi
	movq	%rsi, 32(%r10,%rdi)
	jmp	.L137
.L275:
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %edx
	cmpl	$4096, %edx
	jne	.L134
.L224:
	imulq	$248, %rcx, %r11
	movq	(%rsi), %rsi
	movq	%rsi, 16(%r10,%r11)
	jmp	.L132
.L273:
	movslq	68(%rsp), %rdi
	salq	$4, %rdi
	addq	Rint@GOTPCREL(%rip), %rdi
	movl	8(%rdi), %esi
	cmpl	$4096, %esi
	jne	.L145
.L228:
	imulq	$248, %rcx, %r11
	movq	(%rdi), %rdi
	movq	%rdi, 16(%r10,%r11)
	jmp	.L143
.L283:
	movslq	72(%rsp), %rcx
	salq	$4, %rcx
	addq	Rint@GOTPCREL(%rip), %rcx
	movl	8(%rcx), %edi
	cmpl	$4096, %edi
	jne	.L111
.L218:
	imulq	$248, %r11, %rsi
	movq	(%rcx), %rcx
	movq	%rcx, 32(%r10,%rsi)
	jmp	.L109
.L259:
	movslq	68(%rsp), %rsi
	salq	$4, %rsi
	addq	Rint@GOTPCREL(%rip), %rsi
	movl	8(%rsi), %ecx
	cmpl	$4096, %ecx
	jne	.L60
.L213:
	imulq	$248, %rax, %rdi
	movq	(%rsi), %rsi
	movq	%rsi, 16(%r10,%rdi)
	jmp	.L58
.L105:
	imulq	$248, %r11, %rcx
	movl	$4096, %edi
	movq	$0, 16(%r10,%rcx)
.L217:
	movq	Rfp@GOTPCREL(%rip), %rcx
	movq	%rcx, 48(%rsp)
	jmp	.L104
.L268:
	imulq	$248, %rax, %rdi
	movq	(%rdx), %rdx
	movq	%rdx, 16(%r10,%rdi)
	jmp	.L78
.L269:
	imulq	$248, %rax, %rsi
	movq	(%rdx), %rdx
	movq	%rdx, 32(%r10,%rsi)
	jmp	.L80
.L263:
	imulq	$248, %rcx, %r8
	movq	(%rdi), %rdi
	movq	%rdi, 32(%r10,%r8)
	jmp	.L96
.L262:
	imulq	$248, %rcx, %r8
	movq	(%rdi), %rdi
	movq	%rdi, 16(%r10,%r8)
	jmp	.L94
.L257:
	imulq	$248, %rcx, %rdi
	movq	(%rax), %rax
	movq	%rax, 16(%r10,%rdi)
	jmp	.L66
.L266:
	imulq	$248, %rax, %rdi
	movq	(%rcx), %rcx
	movq	%rcx, 32(%r10,%rdi)
	jmp	.L86
.L265:
	imulq	$248, %rax, %rdi
	movq	(%rcx), %rcx
	movq	%rcx, 16(%r10,%rdi)
	jmp	.L84
.L59:
	imulq	$248, %rax, %rcx
	movq	$0, 16(%r10,%rcx)
	movl	$4096, %ecx
	jmp	.L58
.L110:
	imulq	$248, %r11, %rcx
	movl	$4096, %edi
	movq	$0, 32(%r10,%rcx)
	jmp	.L109
.L71:
	imulq	$248, %rcx, %rax
	movq	$0, 16(%r10,%rax)
	movl	$4096, %eax
	jmp	.L70
.L138:
	imulq	$248, %rcx, %rdx
	movq	$0, 32(%r10,%rdx)
	movl	$4096, %edx
	jmp	.L137
.L125:
	imulq	$248, %rcx, %rsi
	movl	$4096, %r11d
	movq	$0, 32(%r10,%rsi)
	jmp	.L124
.L120:
	imulq	$248, %rcx, %rsi
	movl	$4096, %r11d
	movq	$0, 16(%r10,%rsi)
	jmp	.L119
.L133:
	imulq	$248, %rcx, %rdx
	movq	$0, 16(%r10,%rdx)
	movl	$4096, %edx
	jmp	.L132
.L144:
	imulq	$248, %rcx, %rsi
	movq	$0, 16(%r10,%rsi)
	movl	$4096, %esi
	jmp	.L143
.L247:
	call	__stack_chk_fail@PLT
.L284:
	imulq	$248, %r11, %rdi
	movq	(%rcx), %rcx
	movq	%rcx, 48(%r10,%rdi)
	jmp	.L114
.L285:
	imulq	$248, %r11, %rsi
	movq	(%rcx), %rcx
	movq	%rcx, 16(%r10,%rsi)
	movq	Rfp@GOTPCREL(%rip), %rcx
	movq	%rcx, 48(%rsp)
	jmp	.L104
.L282:
	imulq	$248, %r11, %rsi
	movq	(%rcx), %rcx
	movq	%rcx, 16(%r10,%rsi)
	jmp	.L104
	.cfi_endproc
.LFE39:
	.size	fase_ISS, .-fase_ISS
	.ident	"GCC: (Ubuntu 11.4.0-1ubuntu1~22.04) 11.4.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 8
	.long	1f - 0f
	.long	4f - 1f
	.long	5
0:
	.string	"GNU"
1:
	.align 8
	.long	0xc0000002
	.long	3f - 2f
2:
	.long	0x3
3:
	.align 8
4:
