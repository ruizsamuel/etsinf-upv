	.file	"prediccion.c"
	.text
	.p2align 4
	.type	cambia_estado_histeresis, @function
cambia_estado_histeresis:
.LFB52:
	.cfi_startproc
	movl	$1, %eax
	movl	%edi, %r9d
	leal	-1(%rdx), %ecx
	movl	%eax, %r8d
	sall	%cl, %r8d
	testl	%r9d, %r9d
	movl	(%rsi), %edi
	je	.L2
	movl	%edx, %ecx
	subl	$1, %r8d
	sall	%cl, %eax
	subl	$1, %eax
	cmpl	%edi, %r8d
	je	.L7
	cmpl	%edi, %eax
	jle	.L1
	addl	$1, %edi
	movl	%edi, (%rsi)
	ret
	.p2align 4,,7
	.p2align 3
.L2:
	cmpl	%r8d, %edi
	je	.L8
	testl	%edi, %edi
	.p2align 4,,2
	jle	.L1
	subl	$1, %edi
	movl	%edi, (%rsi)
.L1:
	.p2align 4,,1
	ret
	.p2align 4,,7
	.p2align 3
.L8:
	movl	$0, (%rsi)
	ret
	.p2align 4,,7
	.p2align 3
.L7:
	movl	%eax, (%rsi)
	ret
	.cfi_endproc
.LFE52:
	.size	cambia_estado_histeresis, .-cambia_estado_histeresis
	.p2align 4
	.globl	inicializa_prediccion
	.type	inicializa_prediccion, @function
inicializa_prediccion:
.LFB53:
	.cfi_startproc
	endbr64
	movq	config@GOTPCREL(%rip), %rax
	movl	104(%rax), %eax
	testl	%eax, %eax
	jle	.L24
	movq	BTB@GOTPCREL(%rip), %rcx
	leal	-1(%rax), %esi
	movq	%rsi, %rax
	salq	$6, %rax
	addq	%rsi, %rax
	leaq	24(%rcx), %rdx
	salq	$5, %rax
	leaq	2104(%rcx,%rax), %r8
	xorl	%eax, %eax
	.p2align 4
	.p2align 3
.L23:
	testb	$1, %dl
	movq	%rdx, %rdi
	movq	$-1, -24(%rdx)
	movl	$1024, %esi
	movl	$0, -16(%rdx)
	jne	.L63
	testb	$2, %dil
	jne	.L64
.L12:
	testb	$4, %dil
	jne	.L65
.L13:
	movl	%esi, %ecx
	shrl	$3, %ecx
	testb	$4, %sil
	rep stosq
	je	.L14
	movl	$0, (%rdi)
	addq	$4, %rdi
.L14:
	testb	$2, %sil
	je	.L15
	movw	$0, (%rdi)
	addq	$2, %rdi
.L15:
	andl	$1, %esi
	je	.L16
	movb	$0, (%rdi)
.L16:
	testb	$1, %dl
	leaq	1024(%rdx), %rdi
	movl	$1024, %esi
	jne	.L66
.L17:
	testb	$2, %dil
	jne	.L67
	testb	$4, %dil
	jne	.L68
.L19:
	movl	%esi, %ecx
	shrl	$3, %ecx
	testb	$4, %sil
	rep stosq
	je	.L20
	movl	$0, (%rdi)
	addq	$4, %rdi
.L20:
	testb	$2, %sil
	je	.L21
	movw	$0, (%rdi)
	addq	$2, %rdi
.L21:
	andl	$1, %esi
	je	.L22
	movb	$0, (%rdi)
.L22:
	movq	$0, -8(%rdx)
	addq	$2080, %rdx
	movl	$0, -32(%rdx)
	movl	$0, -28(%rdx)
	cmpq	%r8, %rdx
	jne	.L23
.L24:
	movq	BHSR1@GOTPCREL(%rip), %rax
	pxor	%xmm0, %xmm0
	movdqu	%xmm0, (%rax)
	movdqu	%xmm0, 16(%rax)
	movdqu	%xmm0, 32(%rax)
	movdqu	%xmm0, 48(%rax)
	movq	BHSR2@GOTPCREL(%rip), %rax
	movdqu	%xmm0, (%rax)
	movdqu	%xmm0, 16(%rax)
	movdqu	%xmm0, 32(%rax)
	movdqu	%xmm0, 48(%rax)
	ret
	.p2align 4,,7
	.p2align 3
.L67:
	movw	$0, (%rdi)
	addq	$2, %rdi
	subl	$2, %esi
	testb	$4, %dil
	je	.L19
	.p2align 4
	.p2align 3
.L68:
	movl	$0, (%rdi)
	subl	$4, %esi
	addq	$4, %rdi
	jmp	.L19
	.p2align 4,,7
	.p2align 3
.L66:
	movb	$0, 1024(%rdx)
	leaq	1025(%rdx), %rdi
	movl	$1023, %esi
	jmp	.L17
	.p2align 4,,7
	.p2align 3
.L63:
	leaq	1(%rdx), %rdi
	movl	$1023, %esi
	movb	$0, (%rdx)
	testb	$2, %dil
	je	.L12
	.p2align 4
	.p2align 3
.L64:
	movw	$0, (%rdi)
	addq	$2, %rdi
	subl	$2, %esi
	testb	$4, %dil
	je	.L13
	.p2align 4
	.p2align 3
.L65:
	movl	$0, (%rdi)
	subl	$4, %esi
	addq	$4, %rdi
	jmp	.L13
	.cfi_endproc
.LFE53:
	.size	inicializa_prediccion, .-inicializa_prediccion
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"%u %ld %u %ld\n"
.LC1:
	.string	"prediccion.c"
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC2:
	.string	"Error: %s; funci\303\263n:%s; l\303\255nea:%d\n"
	.section	.rodata.str1.1
.LC3:
	.string	"Final de la traza de saltos\n"
	.section	.rodata.str1.8
	.align 8
.LC4:
	.string	"ERROR (%s:%d): Predictor no implementado\n"
	.text
	.p2align 4
	.globl	obtener_prediccion
	.type	obtener_prediccion, @function
obtener_prediccion:
.LFB54:
	.cfi_startproc
	endbr64
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	movq	%rcx, %r15
	movq	%rdi, %rax
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	movq	%rdx, %r12
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	leaq	-40(%rsp), %rsp
	.cfi_def_cfa_offset 96
	movq	config@GOTPCREL(%rip), %rbx
	movq	$0, (%rcx)
	movl	$0, (%rdx)
	movq	%rdi, %rdx
	movslq	128(%rbx), %rcx
	sarq	$63, %rdx
	movq	BHSR1@GOTPCREL(%rip), %r14
	idivq	%rcx
	movslq	136(%rbx), %rcx
	movl	(%r14,%rdx,4), %eax
	movq	%rdi, %rdx
	sarq	$63, %rdx
	movl	%eax, 8(%r8)
	movq	%rdi, %rax
	idivq	%rcx
	movq	BHSR2@GOTPCREL(%rip), %rax
	movl	(%rax,%rdx,4), %eax
	movq	$0, (%r8)
	movl	%eax, 12(%r8)
	movabsq	$-4294967296, %rax
	movq	%rax, 16(%r8)
	movl	108(%rbx), %eax
	cmpl	$11, %eax
	je	.L174
	leal	-9(%rax), %edx
	movq	%rdi, %rbp
	cmpl	$1, %edx
	movl	%esi, %r11d
	jbe	.L72
.L75:
	movl	104(%rbx), %esi
	testl	%esi, %esi
	jle	.L174
	movq	BTB@GOTPCREL(%rip), %rsi
	xorl	%r13d, %r13d
	xorl	%edi, %edi
	leaq	.L106(%rip), %r9
	jmp	.L125
	.p2align 4,,7
	.p2align 3
.L103:
	addl	$1, %edi
	addq	$2080, %rsi
	cmpl	%edi, 104(%rbx)
	jle	.L69
.L125:
	cmpq	%rbp, (%rsi)
	jne	.L103
	movq	estat@GOTPCREL(%rip), %rax
	movl	%edi, 20(%r8)
	movl	%r11d, 2076(%rsi)
	addq	$1, 48(%rax)
	cmpl	$8, 108(%rbx)
	ja	.L104
	movl	108(%rbx), %eax
	movslq	(%r9,%rax,4), %rax
	addq	%r9, %rax
	notrack jmp	*%rax
	.section	.rodata
	.align 4
	.align 4
.L106:
	.long	.L110-.L106
	.long	.L110-.L106
	.long	.L109-.L106
	.long	.L109-.L106
	.long	.L109-.L106
	.long	.L109-.L106
	.long	.L107-.L106
	.long	.L105-.L106
	.long	.L105-.L106
	.text
	.p2align 4,,7
	.p2align 3
.L109:
	movl	112(%rbx), %ecx
	testl	%ecx, %ecx
	je	.L120
	movslq	128(%rbx), %r10
	movq	%rbp, %rdx
	movq	%rbp, %rax
	subl	$1, %ecx
	sarq	$63, %rdx
	idivq	%r10
	movslq	(%r14,%rdx,4), %r10
	movslq	%edi, %rdx
	movq	%rdx, %rax
	salq	$6, %rax
	addq	%rdx, %rax
	leaq	4(%r10,%rax,8), %rdx
	movl	$1, %eax
	sall	%cl, %eax
	movq	BTB@GOTPCREL(%rip), %rcx
	cmpl	%eax, 8(%rcx,%rdx,4)
	jge	.L178
	.p2align 4
	.p2align 3
.L120:
	movl	$0, (%r12)
	xorl	%eax, %eax
.L119:
	addl	$1, %edi
	addq	$2080, %rsi
	cmpl	%edi, 104(%rbx)
	movl	$1, %r13d
	movl	%eax, (%r8)
	movl	$0, 4(%r8)
	movl	$0, 16(%r8)
	jg	.L125
.L69:
	leaq	40(%rsp), %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 56
	movl	%r13d, %eax
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
.L110:
	.cfi_restore_state
	movl	120(%rbx), %ecx
	movl	$1, %r13d
	movl	112(%rbx), %eax
	subl	$1, %ecx
	sall	%cl, %r13d
	testl	%eax, %eax
	jne	.L181
	movq	$0, (%r8)
	cmpl	%r13d, 2072(%rsi)
	jge	.L128
	movl	$0, (%r12)
.L124:
	movl	$0, 16(%r8)
	movl	$1, %r13d
	jmp	.L103
	.p2align 4,,7
	.p2align 3
.L105:
	movl	8(%rsi), %eax
	subl	$2, %eax
	cmpl	$1, %eax
	ja	.L120
.L178:
	movq	16(%rsi), %rax
	movl	$1, (%r12)
	movq	%rax, (%r15)
	movl	$1, %eax
	jmp	.L119
	.p2align 4,,7
	.p2align 3
.L107:
	cmpl	$3, 8(%rsi)
	jne	.L120
	jmp	.L178
	.p2align 4,,7
	.p2align 3
.L181:
	leal	-1(%rax), %ecx
	movl	$1, %r10d
	sall	%cl, %r10d
	movq	%rbp, %rdx
	movslq	128(%rbx), %rcx
	movq	%rbp, %rax
	sarq	$63, %rdx
	idivq	%rcx
	movslq	%edi, %rcx
	movq	%rcx, %rax
	salq	$6, %rax
	addq	%rcx, %rax
	movslq	(%r14,%rdx,4), %rdx
	leaq	4(%rdx,%rax,8), %rax
	movq	BTB@GOTPCREL(%rip), %rdx
	cmpl	%r10d, 8(%rdx,%rax,4)
	jge	.L182
	movl	$0, (%rsp)
	movl	$0, 20(%rsp)
.L122:
	movslq	136(%rbx), %rax
	movq	%rax, %rdx
	movq	%rbp, %rax
	movq	%rdx, 8(%rsp)
	movq	%rbp, %rdx
	sarq	$63, %rdx
	idivq	8(%rsp)
	movq	BHSR2@GOTPCREL(%rip), %rax
	movslq	(%rax,%rdx,4), %rdx
	movq	%rcx, %rax
	salq	$6, %rax
	addq	%rcx, %rax
	leaq	260(%rdx,%rax,8), %rax
	movq	BTB@GOTPCREL(%rip), %rdx
	cmpl	8(%rdx,%rax,4), %r10d
	jg	.L183
	movl	(%rsp), %ecx
	movq	16(%rsi), %rax
	movl	$1, 4(%r8)
	movl	%ecx, (%r8)
	cmpl	2072(%rsi), %r13d
	jg	.L127
	movl	$1, (%r12)
	movq	%rax, (%r15)
	jmp	.L126
	.p2align 4,,7
	.p2align 3
.L72:
	movq	fase_ejecucion_pred@GOTPCREL(%rip), %rdx
	movl	(%rdx), %r13d
	testl	%r13d, %r13d
	je	.L69
	cmpl	$1, %r13d
	jne	.L75
	movq	ciclo1@GOTPCREL(%rip), %rdx
	movq	Ciclo@GOTPCREL(%rip), %rcx
	movl	(%rdx), %edx
	cmpl	%edx, (%rcx)
	je	.L184
	movq	parar_clarividente@GOTPCREL(%rip), %rax
	movl	(%rax), %ecx
	testl	%ecx, %ecx
	jne	.L185
	movq	leer_sig_traza@GOTPCREL(%rip), %r14
	cmpl	$1, (%r14)
	je	.L168
	movq	PC1@GOTPCREL(%rip), %rcx
.L85:
	cmpq	%rbp, (%rcx)
	jne	.L86
	movq	$-1, (%rcx)
	cmpl	$9, 108(%rbx)
	je	.L87
	movl	104(%rbx), %eax
	testl	%eax, %eax
	jle	.L89
	leal	-1(%rax), %eax
	xorl	%esi, %esi
	movq	BTB@GOTPCREL(%rip), %rdx
	movq	%rax, %rcx
	salq	$6, %rcx
	addq	%rax, %rcx
	salq	$5, %rcx
	leaq	2080(%rdx), %rax
	addq	%rax, %rcx
	jmp	.L93
	.p2align 4,,7
	.p2align 3
.L186:
	leaq	2080(%rax), %rax
.L93:
	cmpq	%rbp, (%rdx)
	jne	.L92
	movl	%r11d, 2076(%rdx)
	movl	$1, %esi
.L92:
	cmpq	%rax, %rcx
	movq	%rax, %rdx
	jne	.L186
	testl	%esi, %esi
	je	.L89
.L87:
	movq	estat@GOTPCREL(%rip), %rax
	addq	$1, 48(%rax)
	movq	cond1@GOTPCREL(%rip), %rax
	movl	(%rax), %edx
	testl	%edx, %edx
	jne	.L90
	movl	$0, (%r12)
.L91:
	movl	$1, (%r14)
	jmp	.L69
.L86:
	movq	IF_ISS_1@GOTPCREL(%rip), %rax
	movslq	132(%rax), %rax
	leaq	(%rax,%rax,2), %rax
	salq	$4, %rax
	addq	instruction_list@GOTPCREL(%rip), %rax
	movzbl	44(%rax), %eax
	movl	%eax, %edx
	andl	$123, %eax
	andl	$127, %edx
	cmpb	$99, %al
	je	.L96
	cmpb	$111, %dl
	jne	.L97
.L96:
	cmpl	$9, 108(%rbx)
	je	.L173
	movl	104(%rbx), %eax
	testl	%eax, %eax
	jle	.L97
	leal	-1(%rax), %eax
	xorl	%esi, %esi
	movq	BTB@GOTPCREL(%rip), %rdx
	movq	%rax, %rcx
	salq	$6, %rcx
	addq	%rax, %rcx
	salq	$5, %rcx
	leaq	2080(%rdx), %rax
	addq	%rax, %rcx
	jmp	.L102
.L187:
	leaq	2080(%rax), %rax
.L102:
	cmpq	%rbp, (%rdx)
	jne	.L101
	movl	%r11d, 2076(%rdx)
	movl	$1, %esi
.L101:
	cmpq	%rax, %rcx
	movq	%rax, %rdx
	jne	.L187
	testl	%esi, %esi
	je	.L97
.L173:
	movq	estat@GOTPCREL(%rip), %rax
	addq	$1, 48(%rax)
.L97:
	movl	$0, (%r14)
	.p2align 4
	.p2align 3
.L174:
	xorl	%r13d, %r13d
	jmp	.L69
	.p2align 4,,7
	.p2align 3
.L183:
	movl	(%rsp), %eax
	movl	$0, 4(%r8)
	movl	%eax, (%r8)
	cmpl	2072(%rsi), %r13d
	jg	.L127
	.p2align 4
	.p2align 3
.L128:
	movl	$0, (%r12)
.L126:
	movl	$1, 16(%r8)
	movl	$1, %r13d
	jmp	.L103
	.p2align 4,,7
	.p2align 3
.L182:
	movq	16(%rsi), %rax
	movl	$1, (%rsp)
	movl	$1, 20(%rsp)
	movq	%rax, 24(%rsp)
	jmp	.L122
	.p2align 4,,7
	.p2align 3
.L127:
	movl	20(%rsp), %eax
	movl	%eax, (%r12)
	testl	%eax, %eax
	je	.L124
	movq	24(%rsp), %rax
	movq	%rax, (%r15)
	jmp	.L124
.L168:
	movq	f_trace@GOTPCREL(%rip), %rax
	movq	PC1@GOTPCREL(%rip), %rcx
	movq	dest1@GOTPCREL(%rip), %r9
	movq	cond1@GOTPCREL(%rip), %r8
	movq	orden1@GOTPCREL(%rip), %rdx
	movq	(%rax), %rdi
	xorl	%eax, %eax
	movl	%esi, 8(%rsp)
	leaq	.LC0(%rip), %rsi
	movq	%rcx, (%rsp)
	call	__isoc99_fscanf@PLT
	movq	orden1@GOTPCREL(%rip), %rax
	movq	(%rsp), %rcx
	movl	8(%rsp), %r11d
	cmpl	$-2, (%rax)
	jne	.L85
	movq	stderr@GOTPCREL(%rip), %rbx
	movl	$265, %r9d
	leaq	__func__.0(%rip), %r8
	leaq	.LC1(%rip), %rcx
	leaq	.LC2(%rip), %rdx
	movl	$1, %esi
	movq	(%rbx), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movq	(%rbx), %rcx
	movl	$28, %edx
	movl	$1, %esi
	leaq	.LC3(%rip), %rdi
	call	fwrite@PLT
	call	abort@PLT
	.p2align 4,,7
	.p2align 3
.L185:
	movl	104(%rbx), %eax
	testl	%eax, %eax
	jle	.L174
	leal	-1(%rax), %eax
	xorl	%esi, %esi
	movq	BTB@GOTPCREL(%rip), %rdx
	movq	%rax, %rcx
	salq	$6, %rcx
	addq	%rax, %rcx
	salq	$5, %rcx
	leaq	2080(%rdx), %rax
	addq	%rax, %rcx
	jmp	.L84
	.p2align 4,,7
	.p2align 3
.L188:
	leaq	2080(%rax), %rax
.L84:
	cmpq	%rbp, (%rdx)
	jne	.L83
	movl	%r11d, 2076(%rdx)
	movl	$1, %esi
.L83:
	cmpq	%rcx, %rax
	movq	%rax, %rdx
	jne	.L188
.L167:
	testl	%esi, %esi
	je	.L174
.L176:
	movq	estat@GOTPCREL(%rip), %rax
	xorl	%r13d, %r13d
	addq	$1, 48(%rax)
	jmp	.L69
.L184:
	movq	IF_ISS_1@GOTPCREL(%rip), %rdx
	movslq	132(%rdx), %rdx
	leaq	(%rdx,%rdx,2), %rdx
	salq	$4, %rdx
	addq	instruction_list@GOTPCREL(%rip), %rdx
	movzbl	44(%rdx), %edx
	movl	%edx, %ecx
	andl	$123, %edx
	andl	$127, %ecx
	cmpb	$99, %dl
	je	.L77
	cmpb	$111, %cl
	jne	.L174
.L77:
	cmpl	$9, %eax
	je	.L176
	movl	104(%rbx), %eax
	testl	%eax, %eax
	jle	.L174
	leal	-1(%rax), %eax
	xorl	%esi, %esi
	movq	BTB@GOTPCREL(%rip), %rdx
	movq	%rax, %rcx
	salq	$6, %rcx
	addq	%rax, %rcx
	salq	$5, %rcx
	leaq	2080(%rdx), %rax
	addq	%rax, %rcx
	jmp	.L81
.L189:
	leaq	2080(%rax), %rax
.L81:
	cmpq	%rbp, (%rdx)
	jne	.L80
	movl	%r11d, 2076(%rdx)
	movl	$1, %esi
.L80:
	cmpq	%rcx, %rax
	movq	%rax, %rdx
	jne	.L189
	jmp	.L167
.L89:
	movq	cond1@GOTPCREL(%rip), %rax
	movl	$0, (%r12)
	movl	(%rax), %eax
	testl	%eax, %eax
	je	.L95
	movq	orden_clarividente@GOTPCREL(%rip), %rax
	movl	%r11d, (%rax)
	movq	parar_clarividente@GOTPCREL(%rip), %rax
	movl	$1, (%rax)
.L95:
	movl	$1, (%r14)
	xorl	%r13d, %r13d
	jmp	.L69
.L90:
	movq	dest1@GOTPCREL(%rip), %rax
	movl	$1, (%r12)
	movq	ciclo1@GOTPCREL(%rip), %rcx
	movq	(%rax), %rax
	movq	%rax, (%r15)
	movq	Ciclo@GOTPCREL(%rip), %rax
	movl	(%rax), %eax
	movl	%eax, (%rcx)
	jmp	.L91
.L104:
	movq	stderr@GOTPCREL(%rip), %rax
	movl	$480, %r8d
	leaq	.LC1(%rip), %rcx
	leaq	.LC4(%rip), %rdx
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	$1, %edi
	call	exit@PLT
	.cfi_endproc
.LFE54:
	.size	obtener_prediccion, .-obtener_prediccion
	.section	.rodata.str1.1
.LC5:
	.string	"%u %ld %d %ld\n"
	.text
	.p2align 4
	.globl	actualizar_prediccion
	.type	actualizar_prediccion, @function
actualizar_prediccion:
.LFB55:
	.cfi_startproc
	endbr64
	pushq	%r15
	.cfi_def_cfa_offset 16
	.cfi_offset 15, -16
	movl	%ecx, %r15d
	pushq	%r14
	.cfi_def_cfa_offset 24
	.cfi_offset 14, -24
	pushq	%r13
	.cfi_def_cfa_offset 32
	.cfi_offset 13, -32
	pushq	%r12
	.cfi_def_cfa_offset 40
	.cfi_offset 12, -40
	movq	%rsi, %r12
	pushq	%rbp
	.cfi_def_cfa_offset 48
	.cfi_offset 6, -48
	pushq	%rbx
	.cfi_def_cfa_offset 56
	.cfi_offset 3, -56
	leaq	-104(%rsp), %rsp
	.cfi_def_cfa_offset 160
	movq	config@GOTPCREL(%rip), %rax
	movl	%edi, 48(%rsp)
	movl	%edx, 52(%rsp)
	movl	108(%rax), %r14d
	movq	%r8, 24(%rsp)
	leal	-9(%r14), %eax
	cmpl	$1, %eax
	jbe	.L265
.L191:
	movl	%r14d, %eax
	andl	$-3, %eax
	cmpl	$9, %eax
	je	.L190
	movq	config@GOTPCREL(%rip), %rax
	movl	104(%rax), %r13d
	testl	%r13d, %r13d
	jle	.L238
	cmpl	$1, %r15d
	movl	$-1, %ebp
	movslq	48(%rsp), %rsi
	sbbl	%eax, %eax
	movq	BTB@GOTPCREL(%rip), %r9
	addl	$3, %eax
	movl	%eax, 76(%rsp)
	cmpl	$1, %r15d
	sbbl	%eax, %eax
	leaq	(%rsi,%rsi,2), %rdx
	andl	$-2, %eax
	leaq	0(,%rdx,4), %rdi
	addl	$3, %eax
	movq	%r9, %r10
	movl	%eax, 80(%rsp)
	xorl	%eax, %eax
	movq	%rsi, 32(%rsp)
	testl	%r15d, %r15d
	setne	%al
	movl	%r15d, %esi
	movl	%eax, 72(%rsp)
	addl	%eax, %eax
	movl	%eax, 68(%rsp)
	movl	$-1, %eax
	xorl	%ebx, %ebx
	movl	%eax, %r15d
	xorl	%ecx, %ecx
	xorl	%r11d, %r11d
	movq	%rdi, 40(%rsp)
	movl	%esi, %eax
	jmp	.L229
	.p2align 4,,7
	.p2align 3
.L195:
	movl	2076(%r10), %edx
	cmpl	%edx, %ebp
	jbe	.L228
	movl	%r11d, %r15d
	movl	%edx, %ebp
.L228:
	addl	$1, %r11d
	addq	$2080, %r10
	addq	$520, %rbx
	cmpl	%r11d, %r13d
	je	.L266
.L229:
	cmpq	%r12, (%r10)
	jne	.L195
	movq	24(%rsp), %rdi
	cmpl	$10, %r14d
	movq	%rdi, 16(%r10)
	ja	.L196
	leaq	.L198(%rip), %rcx
	movl	%r14d, %edx
	movslq	(%rcx,%rdx,4), %rdx
	addq	%rcx, %rdx
	notrack jmp	*%rdx
	.section	.rodata
	.align 4
	.align 4
.L198:
	.long	.L205-.L198
	.long	.L205-.L198
	.long	.L204-.L198
	.long	.L203-.L198
	.long	.L204-.L198
	.long	.L201-.L198
	.long	.L197-.L198
	.long	.L200-.L198
	.long	.L199-.L198
	.long	.L196-.L198
	.long	.L197-.L198
	.text
	.p2align 4,,7
	.p2align 3
.L204:
	movq	40(%rsp), %rdx
	movq	RB@GOTPCREL(%rip), %rsi
	movq	config@GOTPCREL(%rip), %rdi
	addq	32(%rsp), %rdx
	testl	%eax, %eax
	movl	112(%rdi), %ecx
	leaq	(%rsi,%rdx,8), %rdx
	movslq	56(%rdx), %rdx
	jne	.L267
	movslq	%r11d, %rsi
	movq	%rsi, %rcx
	salq	$6, %rcx
	addq	%rsi, %rcx
	leaq	4(%rdx,%rcx,8), %rcx
	movl	8(%r9,%rcx,4), %edx
	testl	%edx, %edx
	jle	.L259
.L237:
	subl	$1, %edx
	movl	%edx, 8(%r9,%rcx,4)
.L259:
	movl	$1, %ecx
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L266:
	movl	%eax, %ebx
	testl	%ecx, %ecx
	movslq	%r15d, %rax
	movl	%ebx, %r15d
	je	.L194
.L190:
	leaq	104(%rsp), %rsp
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
.L197:
	.cfi_restore_state
	testl	%eax, %eax
	je	.L211
.L209:
	movl	$3, 8(%r10)
	movl	$1, %ecx
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L205:
	movq	40(%rsp), %rdx
	movq	RB@GOTPCREL(%rip), %rsi
	addq	32(%rsp), %rdx
	leaq	(%rsi,%rdx,8), %rdx
	movl	48(%rdx), %ecx
	movl	52(%rdx), %edx
	cmpl	%ecx, %eax
	jne	.L255
	cmpl	%edx, %eax
	je	.L222
	movl	2072(%r10), %edx
	testl	%edx, %edx
	jle	.L223
	subl	$1, %edx
	movl	%edx, 2072(%r10)
.L223:
	movq	40(%rsp), %rdx
	movq	config@GOTPCREL(%rip), %rdi
	movq	RB@GOTPCREL(%rip), %rsi
	addq	32(%rsp), %rdx
	movl	112(%rdi), %ecx
	cmpb	$104, 116(%rdi)
	leaq	(%rsi,%rdx,8), %r8
	movslq	56(%r8), %rdx
	je	.L268
	testl	%eax, %eax
	movslq	%r11d, %rsi
	je	.L225
	movq	%rsi, %rdi
	salq	$6, %rdi
	addq	%rsi, %rdi
	leaq	4(%rdx,%rdi,8), %rdx
	movl	$1, %edi
	movl	8(%r9,%rdx,4), %r8d
	sall	%cl, %edi
	leal	-1(%rdi), %ecx
	cmpl	%ecx, %r8d
	jge	.L226
	addl	$1, %r8d
	movl	%r8d, 8(%r9,%rdx,4)
.L226:
	movq	40(%rsp), %rdx
	movq	RB@GOTPCREL(%rip), %rdi
	addq	32(%rsp), %rdx
	movslq	60(%rdi,%rdx,8), %rdi
	movq	%rsi, %rdx
	salq	$6, %rdx
	addq	%rsi, %rdx
	leaq	260(%rdi,%rdx,8), %rsi
	movl	8(%r9,%rsi,4), %edx
	cmpl	%edx, %ecx
	jle	.L259
	addl	$1, %edx
	movl	$1, %ecx
	movl	%edx, 8(%r9,%rsi,4)
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L267:
	movslq	%r11d, %rdi
	movq	%rdi, %rsi
	salq	$6, %rsi
	addq	%rdi, %rsi
	leaq	4(%rdx,%rsi,8), %rdi
	movl	$1, %edx
	movl	8(%r9,%rdi,4), %esi
	sall	%cl, %edx
	subl	$1, %edx
	cmpl	%edx, %esi
	jge	.L259
	addl	$1, %esi
	movl	$1, %ecx
	movl	%esi, 8(%r9,%rdi,4)
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L199:
	movl	8(%r10), %edx
	cmpl	$2, %edx
	je	.L214
	ja	.L215
	testl	%edx, %edx
	.p2align 4,,2
	je	.L269
	movl	68(%rsp), %esi
	movl	$1, %ecx
	movl	%esi, 8(%r10)
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L203:
	movq	config@GOTPCREL(%rip), %rsi
	movl	112(%rsi), %r8d
.L260:
	movq	40(%rsp), %rdx
	movq	RB@GOTPCREL(%rip), %rdi
	movq	%r9, 56(%rsp)
	movl	%eax, 64(%rsp)
	addq	32(%rsp), %rdx
	leaq	(%rdi,%rdx,8), %rdx
	movl	%eax, %edi
	movslq	56(%rdx), %rdx
	leaq	6(%rbx,%rdx), %rdx
	leaq	(%r9,%rdx,4), %rsi
	movl	%r8d, %edx
	call	cambia_estado_histeresis
	movl	64(%rsp), %eax
	movl	$1, %ecx
	movq	56(%rsp), %r9
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L201:
	movq	config@GOTPCREL(%rip), %rdi
	movl	112(%rdi), %r8d
	jmp	.L260
	.p2align 4,,7
	.p2align 3
.L200:
	movl	8(%r10), %ecx
	testl	%eax, %eax
	je	.L207
	cmpl	$1, %ecx
	je	.L208
	cmpl	$2, %ecx
	je	.L209
	testl	%ecx, %ecx
	.p2align 4,,3
	jne	.L259
	movl	$1, 8(%r10)
	movl	$1, %ecx
	jmp	.L195
.L238:
	movq	BTB@GOTPCREL(%rip), %r9
	movq	$-1, %rax
	.p2align 4
	.p2align 3
.L194:
	movl	52(%rsp), %ebx
	movq	%rax, %rdx
	salq	$6, %rdx
	addq	%rax, %rdx
	salq	$5, %rdx
	addq	%r9, %rdx
	cmpl	$8, %r14d
	movl	%ebx, 2076(%rdx)
	movq	24(%rsp), %rbx
	movq	%r12, (%rdx)
	movq	%rbx, 16(%rdx)
	ja	.L231
	cmpl	$5, %r14d
	ja	.L232
	movslq	48(%rsp), %rdx
	leaq	(%rdx,%rdx,2), %rcx
	leaq	(%rdx,%rcx,4), %rdx
	movq	RB@GOTPCREL(%rip), %rcx
	leaq	(%rcx,%rdx,8), %rdx
	xorl	%ecx, %ecx
	movslq	56(%rdx), %rdi
	testl	%r15d, %r15d
	movslq	60(%rdx), %rsi
	je	.L235
	movq	config@GOTPCREL(%rip), %rbx
	movl	$1, %edx
	movl	112(%rbx), %ecx
	sall	%cl, %edx
	leal	-1(%rdx), %ecx
.L235:
	movq	%rax, %rdx
	salq	$6, %rdx
	addq	%rdx, %rax
	leaq	0(,%rax,8), %rdx
	salq	$5, %rax
	leaq	4(%rdi,%rdx), %rdi
	movl	%ecx, 8(%r9,%rdi,4)
	leaq	260(%rdx,%rsi), %rdx
	movl	$0, 2072(%r9,%rax)
	movl	%ecx, 8(%r9,%rdx,4)
	leaq	104(%rsp), %rsp
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
.L215:
	.cfi_restore_state
	cmpl	$3, %edx
	movl	$1, %ecx
	jne	.L195
	movl	76(%rsp), %esi
	movl	%esi, 8(%r10)
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L265:
	movq	fase_ejecucion_pred@GOTPCREL(%rip), %rax
	movl	(%rax), %r9d
	testl	%r9d, %r9d
	jne	.L191
	movq	f_trace@GOTPCREL(%rip), %rax
	movl	%ecx, %r9d
	movq	%r8, (%rsp)
	movl	%edx, %ecx
	movq	%rsi, %r8
	leaq	.LC5(%rip), %rdx
	movq	(%rax), %rdi
	movl	$1, %esi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movq	config@GOTPCREL(%rip), %rax
	movl	108(%rax), %r14d
	jmp	.L191
.L231:
	cmpl	$10, %r14d
	jne	.L190
.L232:
	movq	%rax, %rdx
	salq	$6, %rdx
	addq	%rdx, %rax
	salq	$5, %rax
	testl	%r15d, %r15d
	je	.L234
	movl	$3, 8(%r9,%rax)
	jmp	.L190
	.p2align 4,,7
	.p2align 3
.L207:
	cmpl	$2, %ecx
	jne	.L270
	.p2align 4
	.p2align 3
.L211:
	movl	$0, 8(%r10)
	movl	$1, %ecx
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L270:
	cmpl	$3, %ecx
	je	.L212
	cmpl	$1, %ecx
	jne	.L259
	movl	$0, 8(%r10)
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L225:
	movq	%rsi, %rcx
	salq	$6, %rcx
	addq	%rsi, %rcx
	leaq	4(%rdx,%rcx,8), %rcx
	movl	8(%r9,%rcx,4), %edx
	testl	%edx, %edx
	jle	.L227
	subl	$1, %edx
	movl	%edx, 8(%r9,%rcx,4)
.L227:
	movq	40(%rsp), %rdx
	movq	RB@GOTPCREL(%rip), %rdi
	addq	32(%rsp), %rdx
	movslq	60(%rdi,%rdx,8), %rcx
	movq	%rsi, %rdx
	salq	$6, %rdx
	addq	%rsi, %rdx
	leaq	260(%rcx,%rdx,8), %rcx
	movl	8(%r9,%rcx,4), %edx
	testl	%edx, %edx
	jg	.L237
	jmp	.L259
	.p2align 4,,7
	.p2align 3
.L222:
	cmpl	%ecx, %eax
	je	.L223
.L255:
	cmpl	%edx, %eax
	.p2align 4,,7
	jne	.L223
	movq	config@GOTPCREL(%rip), %rdi
	movl	$1, %edx
	movl	2072(%r10), %esi
	movl	120(%rdi), %ecx
	sall	%cl, %edx
	subl	$1, %edx
	cmpl	%edx, %esi
	jge	.L223
	addl	$1, %esi
	movl	%esi, 2072(%r10)
	jmp	.L223
	.p2align 4,,7
	.p2align 3
.L268:
	leaq	6(%rbx,%rdx), %rdx
	movl	%eax, %edi
	movl	%ecx, 84(%rsp)
	leaq	(%r9,%rdx,4), %rsi
	movl	%eax, 64(%rsp)
	movl	%ecx, %edx
	movq	%r8, 88(%rsp)
	movq	%r9, 56(%rsp)
	call	cambia_estado_histeresis
	movq	88(%rsp), %r8
	movq	56(%rsp), %r9
	movl	64(%rsp), %edi
	movslq	60(%r8), %rdx
	leaq	262(%rbx,%rdx), %rdx
	leaq	(%r9,%rdx,4), %rsi
	movl	84(%rsp), %edx
	call	cambia_estado_histeresis
	movl	64(%rsp), %eax
	movl	$1, %ecx
	movq	56(%rsp), %r9
	jmp	.L195
	.p2align 4,,7
	.p2align 3
.L269:
	movl	72(%rsp), %esi
	movl	$1, %ecx
	movl	%esi, 8(%r10)
	jmp	.L195
.L214:
	movl	80(%rsp), %esi
	movl	$1, %ecx
	movl	%esi, 8(%r10)
	jmp	.L195
.L234:
	movl	$0, 8(%r9,%rax)
	jmp	.L190
	.p2align 4,,7
	.p2align 3
.L196:
	movq	stderr@GOTPCREL(%rip), %rax
	movl	$692, %r8d
	leaq	.LC1(%rip), %rcx
	leaq	.LC4(%rip), %rdx
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	$1, %edi
	call	exit@PLT
	.p2align 4,,7
	.p2align 3
.L212:
	movl	$2, 8(%r10)
	movl	$1, %ecx
	jmp	.L195
.L208:
	movl	$3, 8(%r10)
	jmp	.L195
	.cfi_endproc
.LFE55:
	.size	actualizar_prediccion, .-actualizar_prediccion
	.section	.rodata
	.align 16
	.type	__func__.0, @object
	.size	__func__.0, 19
__func__.0:
	.string	"obtener_prediccion"
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
