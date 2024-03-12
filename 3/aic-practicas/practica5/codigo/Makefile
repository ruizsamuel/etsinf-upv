PRAC=5

CFLAGS		= -I. -O2 -fno-strength-reduce -fPIC -Wall -Wno-unused -Wno-unused-result -Wno-format -Wno-switch -Wno-maybe-uninitialized
LFLAGS		= -lm
CC		= gcc

TARGET=riscv-ooo
OBJECTS=codop.o enlazador.o estado.o f_busqueda.o f_confirmacion.o f_ejecucion.o f_lanzamiento.o f_transferencia.o g_color.o g_html.o g_options.o g_string.o html-style.o input.lex.o input.yacc.o instrucciones.o jerarquia.o jwrite.o main.o memoria.o memoria-cache.o memoria-principal.o prediccion.o presentacion.o presentacion-html.o presentacion-json.o registros.o riscv_syscall.o simbolos.o f_lanzamiento_alum.o f_transferencia_alum.o

all: $(TARGET)

$(TARGET): $(OBJECTS)
	@echo " [LINK] Building exec file '${TARGET}'"
	@$(CC) $(OBJECTS) -o $(TARGET) $(LFLAGS)

clean:
	rm -f $(OBJECTS) $(TARGET)

%.o: %.c
	@echo " [GCC] Building object file '$@' using $<"
	@$(CC) -c $(CFLAGS) -o $@ -D PRAC=$(PRAC) $<

%.s: %.c
	@echo " [GCC] Building asm file '$@' using $<"
	@$(CC) -S $(CFLAGS) -mtune=intel -o $@ -D PRAC=$(PRAC) $<

%.o: %.s
	@echo " [GCC] Building object file '$@' using $<"
	@$(CC) -c $(CFLAGS) -o $@ -D PRAC=$(PRAC) $<

%.lex.c: %.lex.l
	@echo " [FLEX] Building scanner file '$@' using $<"
	@lex -t $*.lex.l > $*.lex.c

input.lex.o: input.yacc.h

%.yacc.h: %.yacc.y
	@echo " [BISON] Building parser file '$@' using $<"
	@bison -o $*.yacc.c -d $*.yacc.y

%.yacc.c: %.yacc.y
	@echo " [BISON] Building parser file '$@' using $<"
	@bison -v -o $*.yacc.c $*.yacc.y 

