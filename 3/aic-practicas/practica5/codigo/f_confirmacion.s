	.file	"f_confirmacion.c"
	.text
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"x"
.LC1:
	.string	"X"
	.text
	.p2align 4
	.globl	cancelar_instrucciones
	.type	cancelar_instrucciones, @function
cancelar_instrucciones:
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
	leaq	-24(%rsp), %rsp
	.cfi_def_cfa_offset 80
	movq	config@GOTPCREL(%rip), %rbx
	movq	RB@GOTPCREL(%rip), %rax
	movl	36(%rbx), %r9d
	movq	%rax, 8(%rsp)
	testl	%r9d, %r9d
	jle	.L40
	movq	%rax, %r14
	xorl	%r13d, %r13d
	movq	RB_inicio@GOTPCREL(%rip), %rbp
	leaq	.LC0(%rip), %r15
	.p2align 4
	.p2align 3
.L4:
	movl	(%r14), %r8d
	testl	%r8d, %r8d
	je	.L3
	cmpl	%r13d, 0(%rbp)
	je	.L3
	movl	100(%r14), %esi
	xorl	%edx, %edx
	movq	presentacion@GOTPCREL(%rip), %rax
	movq	%r15, %rdi
	call	*72(%rax)
.L3:
	addl	$1, %r13d
	addq	$104, %r14
	cmpl	%r13d, 36(%rbx)
	jg	.L4
.L2:
	movl	40(%rbx), %edi
	testl	%edi, %edi
	jle	.L5
	movq	RS@GOTPCREL(%rip), %r13
	xorl	%r14d, %r14d
	leaq	.LC1(%rip), %r15
	jmp	.L8
	.p2align 4,,7
	.p2align 3
.L6:
	addl	$1, %r14d
	addq	$248, %r13
	cmpl	%r14d, 40(%rbx)
	jle	.L5
.L8:
	movl	0(%r13), %esi
	testl	%esi, %esi
	je	.L6
	cmpl	$3, 204(%r13)
	jne	.L6
	movl	20(%rbx), %eax
	addl	16(%rbx), %eax
	addl	24(%rbx), %eax
	cmpl	%r14d, %eax
	jg	.L7
	addl	28(%rbx), %eax
	cmpl	%r14d, %eax
	jg	.L6
.L7:
	movl	244(%r13), %esi
	xorl	%edx, %edx
	movq	presentacion@GOTPCREL(%rip), %rax
	movq	%r15, %rdi
	call	*72(%rax)
	jmp	.L6
	.p2align 4,,7
	.p2align 3
.L5:
	movq	NUM_OPERADORES@GOTPCREL(%rip), %r12
	movl	(%r12), %esi
	testl	%esi, %esi
	jle	.L9
	movq	Op@GOTPCREL(%rip), %r14
	xorl	%r15d, %r15d
	leaq	.LC1(%rip), %r13
	.p2align 4
	.p2align 3
.L12:
	movl	(%r14), %ecx
	testl	%ecx, %ecx
	je	.L10
	movl	20(%rbx), %eax
	movslq	8(%r14), %rdx
	addl	16(%rbx), %eax
	addl	24(%rbx), %eax
	cmpl	%eax, %edx
	jl	.L11
	addl	28(%rbx), %eax
	cmpl	%eax, %edx
	jl	.L10
.L11:
	movq	%rdx, %rax
	movq	%r13, %rdi
	salq	$5, %rax
	subq	%rdx, %rax
	movq	RS@GOTPCREL(%rip), %rdx
	leaq	(%rdx,%rax,8), %rax
	xorl	%edx, %edx
	movl	244(%rax), %esi
	movq	presentacion@GOTPCREL(%rip), %rax
	call	*72(%rax)
.L10:
	movl	(%r12), %esi
	addl	$1, %r15d
	addq	$28, %r14
	cmpl	%r15d, %esi
	jg	.L12
.L9:
	movq	Rfp@GOTPCREL(%rip), %rcx
	movl	$8, %eax
	movq	Rint@GOTPCREL(%rip), %rdx
	.p2align 4
	.p2align 3
.L13:
	movl	$4096, (%rax,%rcx)
	addq	$16, %rax
	movl	$4096, -16(%rax,%rdx)
	cmpq	$520, %rax
	jne	.L13
	movl	40(%rbx), %eax
	testl	%eax, %eax
	jle	.L14
	movq	RS@GOTPCREL(%rip), %rdx
	leal	-1(%rax), %eax
	movq	%rax, %rcx
	salq	$5, %rcx
	subq	%rax, %rcx
	leaq	248(%rdx), %rax
	leaq	(%rax,%rcx,8), %rcx
	jmp	.L17
	.p2align 4,,7
	.p2align 3
.L41:
	leaq	248(%rax), %rax
.L17:
	movl	(%rdx), %r11d
	testl	%r11d, %r11d
	je	.L15
	movl	200(%rdx), %r10d
	testl	%r10d, %r10d
	jne	.L16
.L15:
	movl	$0, (%rdx)
.L16:
	cmpq	%rcx, %rax
	movq	%rax, %rdx
	jne	.L41
.L14:
	testl	%esi, %esi
	jle	.L18
	movq	Op@GOTPCREL(%rip), %rdx
	leal	-1(%rsi), %eax
	leaq	0(,%rax,8), %rcx
	subq	%rax, %rcx
	leaq	28(%rdx), %rax
	leaq	(%rax,%rcx,4), %rsi
	jmp	.L21
	.p2align 4,,7
	.p2align 3
.L20:
	movl	$0, (%rdx)
.L19:
	cmpq	%rsi, %rax
	movq	%rax, %rdx
	je	.L18
.L42:
	leaq	28(%rax), %rax
.L21:
	movl	(%rdx), %r9d
	testl	%r9d, %r9d
	je	.L19
	cmpl	$3, 4(%rdx)
	jne	.L20
	movslq	8(%rdx), %rdi
	movq	%rdi, %rcx
	salq	$5, %rcx
	subq	%rdi, %rcx
	movq	RS@GOTPCREL(%rip), %rdi
	leaq	(%rdi,%rcx,8), %rcx
	movl	200(%rcx), %r8d
	testl	%r8d, %r8d
	je	.L20
	cmpq	%rsi, %rax
	movq	%rax, %rdx
	jne	.L42
	.p2align 4
	.p2align 3
.L18:
	movslq	0(%rbp), %rax
	movq	8(%rsp), %rdi
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rax
	leaq	(%rdi,%rax,8), %rax
	movl	40(%rax), %ecx
	testl	%ecx, %ecx
	jne	.L22
	movq	88(%rax), %rax
	addq	$4, %rax
.L23:
	movq	Control_1@GOTPCREL(%rip), %rdx
	movq	%rax, (%rdx)
	movq	Cancelar_Activa@GOTPCREL(%rip), %rax
	movl	$1, (%rax)
	movq	Excepcion_Activa@GOTPCREL(%rip), %rax
	movl	$0, (%rax)
	movl	36(%rbx), %eax
	testl	%eax, %eax
	jle	.L24
	movq	8(%rsp), %r12
	leal	-1(%rax), %eax
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	leaq	104(%r12), %rax
	leaq	(%rax,%rdx,8), %rdx
	jmp	.L25
	.p2align 4,,7
	.p2align 3
.L43:
	leaq	104(%rax), %rax
.L25:
	movl	$0, (%r12)
	cmpq	%rax, %rdx
	movl	$0, 80(%r12)
	movq	%rax, %r12
	jne	.L43
.L24:
	movq	L1D@GOTPCREL(%rip), %rdi
	movq	RB_long@GOTPCREL(%rip), %rax
	movl	$0, 0(%rbp)
	movl	(%rdi), %edx
	movl	$0, (%rax)
	movq	RB_fin@GOTPCREL(%rip), %rax
	testl	%edx, %edx
	movl	$0, (%rax)
	jne	.L44
	movq	L1I@GOTPCREL(%rip), %rdi
	movl	(%rdi), %eax
	testl	%eax, %eax
	jne	.L45
.L1:
	leaq	24(%rsp), %rsp
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
.L22:
	.cfi_restore_state
	movq	32(%rax), %rax
	jmp	.L23
.L44:
	movl	$1, %esi
	call	cancela_operaciones_mem_cache@PLT
	movq	L1I@GOTPCREL(%rip), %rdi
	movl	(%rdi), %eax
	testl	%eax, %eax
	je	.L1
.L45:
	leaq	24(%rsp), %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 56
	movl	$1, %esi
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
	jmp	cancela_operaciones_mem_cache@PLT
.L40:
	.cfi_restore_state
	movq	RB_inicio@GOTPCREL(%rip), %rbp
	jmp	.L2
	.cfi_endproc
.LFE39:
	.size	cancelar_instrucciones, .-cancelar_instrucciones
	.p2align 4
	.globl	prediccion_correcta
	.type	prediccion_correcta, @function
prediccion_correcta:
.LFB40:
	.cfi_startproc
	endbr64
	movq	RB_inicio@GOTPCREL(%rip), %rax
	movslq	(%rax), %rax
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rax
	movq	RB@GOTPCREL(%rip), %rdx
	leaq	(%rdx,%rax,8), %rax
	movslq	4(%rax), %rdx
	movl	44(%rax), %esi
	movl	40(%rax), %ecx
	leaq	(%rdx,%rdx,2), %rdx
	salq	$4, %rdx
	addq	instruction_list@GOTPCREL(%rip), %rdx
	movzbl	44(%rdx), %edx
	andl	$119, %edx
	cmpb	$103, %dl
	je	.L47
	xorl	%r8d, %r8d
	cmpl	%ecx, %esi
	sete	%r8b
.L46:
	movl	%r8d, %eax
	ret
	.p2align 4,,7
	.p2align 3
.L47:
	xorl	%r8d, %r8d
	cmpl	%ecx, %esi
	jne	.L46
	movq	72(%rax), %rdi
	xorl	%r8d, %r8d
	cmpq	%rdi, 32(%rax)
	sete	%r8b
	movl	%r8d, %eax
	ret
	.cfi_endproc
.LFE40:
	.size	prediccion_correcta, .-prediccion_correcta
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC2:
	.string	"Ciclo %u: Acceso a memoria de instrucciones ilegal. PC=%ld (%s)\n"
	.align 8
.LC3:
	.string	"Ciclo %u: Acceso a memoria de datos ilegal. PC=%ld (%s)\n"
	.align 8
.LC4:
	.string	"Ciclo %u: Acceso desalineado a memoria de datos. PC=%ld (%s)\n"
	.align 8
.LC5:
	.string	"Ciclo %u: Excepci\303\263n no manejada. PC=%ld (%s)\n"
	.section	.rodata.str1.1
.LC6:
	.string	"C"
.LC7:
	.string	"f_confirmacion.c"
	.section	.rodata.str1.8
	.align 8
.LC8:
	.string	"ERROR (%s:%d): Operacion %d no implementada\n"
	.text
	.p2align 4
	.globl	fase_COM
	.type	fase_COM, @function
fase_COM:
.LFB43:
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
	leaq	-152(%rsp), %rsp
	.cfi_def_cfa_offset 208
	movq	terminando@GOTPCREL(%rip), %r12
	movq	RB_inicio@GOTPCREL(%rip), %rbx
	movq	RB_cab@GOTPCREL(%rip), %rdx
	movl	(%r12), %r14d
	movq	%fs:40, %rax
	movq	%rax, 136(%rsp)
	xorl	%eax, %eax
	movslq	(%rbx), %rax
	testl	%r14d, %r14d
	movl	%eax, (%rdx)
	jne	.L51
	movq	RB@GOTPCREL(%rip), %rbp
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	leaq	0(%rbp,%rdx,8), %rdx
	movl	(%rdx), %r13d
	testl	%r13d, %r13d
	jne	.L165
.L51:
	movq	136(%rsp), %rax
	subq	%fs:40, %rax
	jne	.L166
	leaq	152(%rsp), %rsp
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
.L165:
	.cfi_restore_state
	movslq	4(%rdx), %rsi
	movq	instruction_list@GOTPCREL(%rip), %r14
	movl	8(%rdx), %r11d
	movq	%rsi, %rcx
	leaq	(%rsi,%rsi,2), %rsi
	salq	$4, %rsi
	movzbl	44(%r14,%rsi), %esi
	andl	$123, %esi
	testl	%r11d, %r11d
	je	.L167
	movq	estat@GOTPCREL(%rip), %r13
	addq	$1, 8(%r13)
	cmpb	$35, %sil
	je	.L168
.L60:
	cmpl	$136, %ecx
	jle	.L169
	subl	$167, %ecx
	cmpl	$33, %ecx
	ja	.L64
.L154:
	movabsq	$8594130945, %rdx
	btq	%rcx, %rdx
	jc	.L170
.L64:
	leaq	(%rax,%rax,2), %rdx
	movq	%rsp, %r15
	leaq	(%rax,%rdx,4), %rax
	movq	%r15, %rdi
	movq	88(%rbp,%rax,8), %rsi
	xorl	%edx, %edx
	call	imprime_instruccion_color@PLT
	movq	SIG_SYSCALL@GOTPCREL(%rip), %rax
	movl	$0, (%rax)
	movslq	(%rbx), %rax
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	leaq	0(%rbp,%rdx,8), %rcx
	movl	80(%rcx), %edx
	cmpl	$2, %edx
	je	.L67
	jg	.L68
	testl	%edx, %edx
	jne	.L171
	movq	presentacion@GOTPCREL(%rip), %rdx
	movq	72(%rdx), %rcx
.L86:
	leaq	(%rax,%rax,2), %rdx
	leaq	.LC6(%rip), %rdi
	leaq	(%rax,%rdx,4), %rax
	xorl	%edx, %edx
	movl	100(%rbp,%rax,8), %esi
	call	*%rcx
.L87:
	movslq	(%rbx), %rax
	leaq	(%rax,%rax,2), %rdx
	movq	%rax, %rdi
	leaq	(%rax,%rdx,4), %rdx
	leaq	0(%rbp,%rdx,8), %rcx
	movl	4(%rcx), %r9d
	cmpl	$40, %r9d
	je	.L172
	movslq	%r9d, %rsi
	leaq	(%rsi,%rsi,2), %rdx
	salq	$4, %rdx
	movzbl	44(%r14,%rdx), %edx
	movl	%edx, %r8d
	andl	$127, %r8d
	cmpb	$99, %r8b
	je	.L93
	movl	%edx, %r8d
	andl	$119, %r8d
	cmpb	$103, %r8b
	je	.L173
	andl	$123, %edx
	cmpb	$35, %dl
	je	.L101
	cmpl	$136, %r9d
	jg	.L102
	cmpl	$102, %r9d
	jg	.L174
.L104:
	movq	inst_types_list@GOTPCREL(%rip), %r12
	leaq	(%rsi,%rsi,2), %rdx
	salq	$4, %rdx
	movl	40(%r14,%rdx), %edx
	salq	$4, %rdx
	movl	(%r12,%rdx), %edx
	cmpl	$5, %edx
	je	.L175
	leal	-6(%rdx), %ecx
	andl	$-3, %ecx
	jne	.L108
	leaq	(%rax,%rax,2), %rcx
	cmpl	$8, %edx
	leaq	(%rax,%rcx,4), %rcx
	leaq	0(%rbp,%rcx,8), %rsi
	movq	16(%rsi), %rdi
	je	.L176
.L109:
	imulq	$104, %rax, %rax
	movl	$6, %edx
	movl	$1, %ecx
	addq	%rbp, %rax
	movq	16(%rax), %rdi
	movq	24(%rax), %rsi
	call	escribe_fp_reg@PLT
	movslq	(%rbx), %rax
	imulq	$104, %rax, %rdx
	movq	%rax, %rdi
	movl	4(%rbp,%rdx), %edx
.L110:
	leal	-10180(%rdx), %ecx
	cmpl	$1, %ecx
	jbe	.L119
	leal	-10154(%rdx), %ecx
	cmpl	$3, %ecx
	jbe	.L119
.L111:
	leaq	(%rax,%rax,2), %rcx
	leaq	(%rax,%rcx,4), %rcx
	leaq	0(%rbp,%rcx,8), %rsi
	movq	16(%rsi), %rcx
	movq	24(%rsi), %rsi
	salq	$4, %rcx
	addq	Rfp@GOTPCREL(%rip), %rcx
	movq	%rsi, (%rcx)
	cmpl	%edi, 8(%rcx)
	jne	.L113
	movl	$4096, 8(%rcx)
.L113:
	movslq	%edx, %rcx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	movzbl	44(%r14,%rcx), %ecx
	andl	$127, %ecx
	cmpb	$7, %cl
	je	.L159
	movq	32(%r13), %rcx
	leal	-52(%rdx), %esi
	cmpl	$3, %esi
	jbe	.L114
	subl	$78, %edx
	cmpl	$3, %edx
	jbe	.L114
	addq	$1, %rcx
	movq	%rcx, 32(%r13)
	movq	config@GOTPCREL(%rip), %r13
	jmp	.L99
	.p2align 4,,7
	.p2align 3
.L167:
	cmpb	$35, %sil
	je	.L54
	cmpl	$136, %ecx
	jg	.L55
	cmpl	$102, %ecx
	jle	.L51
	leal	-103(%rcx), %edx
.L152:
	movabsq	$8594130945, %rsi
	btq	%rdx, %rsi
	jnc	.L51
	movq	RS@GOTPCREL(%rip), %r10
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movq	16(%rbp,%rdx,8), %rsi
	movq	%rsi, %rdx
	salq	$5, %rdx
	subq	%rsi, %rdx
	leaq	(%r10,%rdx,8), %rdx
	cmpl	$1, 204(%rdx)
	jne	.L51
	movq	estat@GOTPCREL(%rip), %r13
	addq	$1, 8(%r13)
	jmp	.L60
	.p2align 4,,7
	.p2align 3
.L169:
	cmpl	$102, %ecx
	jle	.L64
	subl	$103, %ecx
	jmp	.L154
	.p2align 4,,7
	.p2align 3
.L55:
	leal	-167(%rcx), %edx
	cmpl	$33, %edx
	jbe	.L152
	jmp	.L51
	.p2align 4,,7
	.p2align 3
.L171:
	cmpl	$1, %edx
	jne	.L71
	movq	Ciclo@GOTPCREL(%rip), %rax
	leaq	.LC2(%rip), %rdx
	movq	88(%rcx), %r8
	movq	%r15, %r9
	movl	$1, %esi
	movl	(%rax), %ecx
	movq	stderr@GOTPCREL(%rip), %rax
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movslq	(%rbx), %rax
	movl	$1, (%r12)
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movl	80(%rbp,%rdx,8), %edx
	jmp	.L74
	.p2align 4,,7
	.p2align 3
.L68:
	cmpl	$4, %edx
	jne	.L177
	movq	Ciclo@GOTPCREL(%rip), %rax
	movq	%r15, %r9
	movq	88(%rcx), %r8
	leaq	.LC4(%rip), %rdx
	movl	(%rax), %ecx
.L164:
	movq	stderr@GOTPCREL(%rip), %rax
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movslq	(%rbx), %rax
	movl	$1, (%r12)
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movslq	4(%rbp,%rdx,8), %rcx
	movq	%rcx, %rdx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	movzbl	44(%r14,%rcx), %ecx
	andl	$123, %ecx
	cmpb	$35, %cl
	je	.L80
	cmpl	$136, %edx
	jg	.L81
	cmpl	$102, %edx
	jg	.L178
.L83:
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movl	80(%rbp,%rdx,8), %edx
	jmp	.L74
	.p2align 4,,7
	.p2align 3
.L177:
	cmpl	$255, %edx
	jne	.L71
	movq	SIG_SYSCALL@GOTPCREL(%rip), %rdi
	movq	presentacion@GOTPCREL(%rip), %r8
	movl	$1, (%rdi)
.L85:
	leaq	(%rax,%rax,2), %rdx
	leaq	.LC6(%rip), %rdi
	leaq	(%rax,%rdx,4), %rax
	movl	$1, %edx
	leaq	0(%rbp,%rax,8), %rax
	movl	100(%rax), %esi
	movl	$40, 4(%rax)
	call	*72(%r8)
	jmp	.L87
	.p2align 4,,7
	.p2align 3
.L173:
	movq	16(%rcx), %rdx
	movq	Rint@GOTPCREL(%rip), %rsi
	testq	%rdx, %rdx
	je	.L95
	movq	24(%rcx), %rcx
	movq	%rdx, %r8
	salq	$4, %r8
	movq	%rcx, (%rsi,%r8)
.L95:
	salq	$4, %rdx
	addq	%rdx, %rsi
	cmpl	8(%rsi), %edi
	jne	.L93
	movl	$4096, 8(%rsi)
	.p2align 4
	.p2align 3
.L93:
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rax
	leaq	0(%rbp,%rax,8), %rax
	movl	80(%rax), %r8d
	testl	%r8d, %r8d
	je	.L179
.L97:
	addq	$1, 56(%r13)
	call	prediccion_correcta@PLT
	testl	%eax, %eax
	je	.L98
	movslq	(%rbx), %rax
	addq	$1, 64(%r13)
	movq	config@GOTPCREL(%rip), %r13
	movq	%rax, %rdi
.L99:
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rax
	leaq	0(%rbp,%rax,8), %rax
	movl	$0, (%rax)
	movl	$0, 80(%rax)
	leal	1(%rdi), %eax
	movl	%eax, %edx
	sarl	$31, %edx
	idivl	36(%r13)
	movq	RB_long@GOTPCREL(%rip), %rax
	subl	$1, (%rax)
	movl	%edx, (%rbx)
	jmp	.L51
	.p2align 4,,7
	.p2align 3
.L54:
	movq	16(%rdx), %rdx
	movq	RS@GOTPCREL(%rip), %rsi
	movq	%rdx, %rcx
	salq	$5, %rcx
	subq	%rdx, %rcx
	cmpl	$1, 204(%rsi,%rcx,8)
	jne	.L51
	movq	estat@GOTPCREL(%rip), %r13
	addq	$1, 8(%r13)
	jmp	.L61
	.p2align 4,,7
	.p2align 3
.L168:
	movq	16(%rdx), %rdx
	movq	RS@GOTPCREL(%rip), %rsi
.L61:
	leaq	(%rax,%rax,2), %rcx
	leaq	(%rax,%rcx,4), %rdi
	movq	%rdx, %rcx
	salq	$5, %rcx
	subq	%rdx, %rcx
	movl	232(%rsi,%rcx,8), %edx
	movl	%edx, 80(%rbp,%rdi,8)
	jmp	.L64
	.p2align 4,,7
	.p2align 3
.L172:
	movq	config@GOTPCREL(%rip), %r13
	movl	20(%r13), %edx
	movl	28(%r13), %esi
	addl	16(%r13), %edx
	addl	24(%r13), %edx
	leal	(%rsi,%rdx), %ecx
	cmpl	%edx, %ecx
	jle	.L89
	movslq	%edx, %rcx
	leal	-1(%rsi), %esi
	movq	RS@GOTPCREL(%rip), %r8
	movq	%rcx, %rdx
	salq	$5, %rdx
	subq	%rcx, %rdx
	addq	%rsi, %rcx
	movq	%rcx, %rsi
	leaq	(%r8,%rdx,8), %rdx
	salq	$5, %rsi
	subq	%rcx, %rsi
	leaq	248(%r8,%rsi,8), %rsi
	jmp	.L91
	.p2align 4,,7
	.p2align 3
.L180:
	addq	$248, %rdx
	cmpq	%rsi, %rdx
	je	.L89
.L91:
	movl	(%rdx), %ecx
	andl	200(%rdx), %ecx
	je	.L180
	jmp	.L51
	.p2align 4,,7
	.p2align 3
.L178:
	subl	$103, %edx
.L156:
	movabsq	$8594130945, %rcx
	btq	%rdx, %rcx
	jnc	.L83
	.p2align 4
	.p2align 3
.L80:
	movq	RS@GOTPCREL(%rip), %r9
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	leaq	0(%rbp,%rdx,8), %rcx
	movq	16(%rcx), %rsi
	movq	%rsi, %rdx
	salq	$5, %rdx
	subq	%rsi, %rdx
	leaq	(%r9,%rdx,8), %rdx
	movl	$0, (%rdx)
	movl	80(%rcx), %edx
.L74:
	movq	presentacion@GOTPCREL(%rip), %r8
	testl	%edx, %edx
	movq	72(%r8), %rcx
	jne	.L85
	jmp	.L86
	.p2align 4,,7
	.p2align 3
.L98:
	movslq	(%rbx), %rax
	movl	$1, %edi
	movq	config@GOTPCREL(%rip), %r10
	leaq	(%rax,%rax,2), %rdx
	movslq	128(%r10), %rcx
	leaq	(%rax,%rdx,4), %rax
	leaq	0(%rbp,%rax,8), %r9
	movq	88(%r9), %r8
	movq	%r8, %rdx
	movq	%r8, %rax
	sarq	$63, %rdx
	idivq	%rcx
	movl	56(%r9), %eax
	movl	132(%r10), %ecx
	leal	(%rax,%rax), %esi
	movl	%edi, %eax
	sall	%cl, %eax
	orl	40(%r9), %esi
	movslq	136(%r10), %rcx
	subl	$1, %eax
	andl	%eax, %esi
	movq	BHSR1@GOTPCREL(%rip), %rax
	movl	%esi, (%rax,%rdx,4)
	movq	%r8, %rdx
	sarq	$63, %rdx
	movq	%r8, %rax
	idivq	%rcx
	movl	60(%r9), %eax
	movl	140(%r10), %ecx
	sall	%cl, %edi
	addl	%eax, %eax
	orl	40(%r9), %eax
	subl	$1, %edi
	movq	BHSR2@GOTPCREL(%rip), %rcx
	andl	%edi, %eax
	movl	%eax, (%rcx,%rdx,4)
	movq	parar_clarividente@GOTPCREL(%rip), %rax
	movl	(%rax), %esi
	testl	%esi, %esi
	je	.L100
	movq	orden_clarividente@GOTPCREL(%rip), %rdx
	movl	(%rdx), %edi
	cmpl	%edi, 100(%r9)
	jne	.L100
	movl	$0, (%rax)
	.p2align 4
	.p2align 3
.L100:
	call	cancelar_instrucciones@PLT
	jmp	.L51
	.p2align 4,,7
	.p2align 3
.L179:
	movq	32(%rax), %r8
	movl	40(%rax), %ecx
	movl	100(%rax), %edx
	movq	88(%rax), %rsi
	call	actualizar_prediccion@PLT
	jmp	.L97
	.p2align 4,,7
	.p2align 3
.L170:
	movq	RS@GOTPCREL(%rip), %rsi
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movq	16(%rbp,%rdx,8), %rdx
	jmp	.L61
	.p2align 4,,7
	.p2align 3
.L67:
	movq	Ciclo@GOTPCREL(%rip), %rax
	movq	%r15, %r9
	movq	88(%rcx), %r8
	leaq	.LC3(%rip), %rdx
	movl	(%rax), %ecx
	jmp	.L164
	.p2align 4,,7
	.p2align 3
.L81:
	subl	$167, %edx
	cmpl	$33, %edx
	jbe	.L156
	jmp	.L83
.L174:
	leal	-103(%r9), %edx
.L158:
	movabsq	$8594130945, %rcx
	btq	%rdx, %rcx
	jnc	.L104
	.p2align 4
	.p2align 3
.L101:
	movq	config@GOTPCREL(%rip), %r13
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movq	16(%rbp,%rdx,8), %rcx
	movq	%rcx, %rdx
	salq	$5, %rdx
	subq	%rcx, %rdx
	movq	RS@GOTPCREL(%rip), %rcx
	leaq	(%rcx,%rdx,8), %rdx
	movl	$1, 200(%rdx)
	jmp	.L99
	.p2align 4,,7
	.p2align 3
.L89:
	leaq	(%rax,%rax,2), %rdx
	movl	$1, %ecx
	leaq	(%rax,%rdx,4), %rax
	leaq	0(%rbp,%rax,8), %rax
	movq	88(%rax), %rsi
	movl	100(%rax), %edx
	leaq	4(%rsi), %r8
	call	actualizar_prediccion@PLT
	call	cancelar_instrucciones@PLT
	movq	SIG_SYSCALL@GOTPCREL(%rip), %rax
	cmpl	$1, (%rax)
	je	.L116
	movslq	(%rbx), %rax
	movq	%rax, %rdi
	jmp	.L99
.L102:
	leal	-167(%r9), %edx
	cmpl	$33, %edx
	jbe	.L158
	jmp	.L104
.L108:
	movq	stderr@GOTPCREL(%rip), %rax
	movl	$336, %r8d
	leaq	.LC7(%rip), %rcx
	leaq	.LC8(%rip), %rdx
	movl	$1, %esi
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movslq	(%rbx), %rax
	movq	%rax, %rdi
.L159:
	movq	config@GOTPCREL(%rip), %r13
	jmp	.L99
.L176:
	movq	24(%rsi), %rsi
	movl	$1, %ecx
	call	escribe_fp_reg@PLT
	movslq	(%rbx), %rax
	leaq	(%rax,%rax,2), %rdx
	movq	%rax, %rdi
	leaq	(%rax,%rdx,4), %rdx
	movslq	4(%rbp,%rdx,8), %rcx
	movq	%rcx, %rdx
	leaq	(%rcx,%rcx,2), %rcx
	salq	$4, %rcx
	movl	40(%r14,%rcx), %ecx
	salq	$4, %rcx
	cmpl	$6, (%r12,%rcx)
	jne	.L110
	jmp	.L109
	.p2align 4,,7
	.p2align 3
.L175:
	leaq	(%rax,%rax,2), %rdx
	leaq	(%rax,%rdx,4), %rdx
	movq	16(%rbp,%rdx,8), %r8
	testq	%r8, %r8
	jne	.L181
.L107:
	salq	$4, %r8
	addq	Rint@GOTPCREL(%rip), %r8
	cmpl	%edi, 8(%r8)
	jne	.L159
	movl	$4096, 8(%r8)
	movq	config@GOTPCREL(%rip), %r13
	jmp	.L99
.L119:
	leaq	(%rax,%rax,2), %rdx
	movl	$1, %ecx
	leaq	(%rax,%rdx,4), %rax
	movl	$7, %edx
	leaq	0(%rbp,%rax,8), %rax
	movq	16(%rax), %rdi
	movq	24(%rax), %rsi
	call	escribe_fp_reg@PLT
	movslq	(%rbx), %rax
	leaq	(%rax,%rax,2), %rdx
	movq	%rax, %rdi
	leaq	(%rax,%rdx,4), %rdx
	movl	4(%rbp,%rdx,8), %edx
	jmp	.L111
.L116:
	movq	SIG_SYSCALL@GOTPCREL(%rip), %rax
	movl	$0, (%rax)
	call	process_syscall@PLT
	movl	%eax, (%r12)
	jmp	.L51
.L114:
	addq	$2, %rcx
	movq	%rcx, 32(%r13)
	movq	config@GOTPCREL(%rip), %r13
	jmp	.L99
.L181:
	imulq	$104, %rax, %rax
	movq	%r8, %rdi
	movl	$5, %edx
	movl	$1, %ecx
	movq	24(%rbp,%rax), %rsi
	call	escribe_int_reg@PLT
	movslq	(%rbx), %rax
	imulq	$104, %rax, %rdx
	movq	%rax, %rdi
	movq	16(%rbp,%rdx), %r8
	jmp	.L107
.L166:
	call	__stack_chk_fail@PLT
.L71:
	imulq	$104, %rax, %rax
	movq	%r15, %r9
	leaq	.LC5(%rip), %rdx
	movl	$1, %esi
	movq	88(%rbp,%rax), %r8
	movq	Ciclo@GOTPCREL(%rip), %rax
	movl	(%rax), %ecx
	movq	stderr@GOTPCREL(%rip), %rax
	movq	(%rax), %rdi
	xorl	%eax, %eax
	call	__fprintf_chk@PLT
	movl	$1, %edi
	call	exit@PLT
	.cfi_endproc
.LFE43:
	.size	fase_COM, .-fase_COM
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
