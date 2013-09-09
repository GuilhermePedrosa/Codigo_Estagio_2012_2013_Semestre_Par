package myBeans;

import GP_Utils.ADFUtils;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;


import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ApplicationModuleImpl;

/**
 * Classe que suporta as diversas funcionalidades da pagina da Human Task Gestão Cursos
 */
public class GC_Helper {


    private static String C_Id_Curso = null;
    private static String C_Name = null;
    private static String C_Descricao = null;
    private static String C_Versao = null;
    private static int C_Duracao = 0;
    private static boolean C_Instanciavel = false;
    private static boolean C_Original = false;
    private static boolean C_Catalogo = false;
    private static String C_CodFornecedor = null;
    private static String C_VersaoFornecedor = null;
    //Dados auxiliares
    private Boolean visibility = false;
    private Boolean disabled = true;
    private static int selectedAction;
    private static boolean Id_Curso_filled = false;
    private static boolean C_Name_filled = false;
    private static boolean C_Versao_filled = false;
    private static boolean Evaluation;

    /*
    * Getters & Setters
    */

    public void setC_Id_Curso(String C_Id_Curso) {
        this.C_Id_Curso = C_Id_Curso;
    }

    public String getC_Id_Curso() {
        return C_Id_Curso;
    }

    public void setC_Name(String C_Name) {
        this.C_Name = C_Name;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Descricao(String C_Descricao) {
        this.C_Descricao = C_Descricao;
    }

    public String getC_Descricao() {
        return C_Descricao;
    }

    public void setC_Duracao(int C_Duracao) {
        this.C_Duracao = C_Duracao;
    }

    public int getC_Duracao() {
        return C_Duracao;
    }

    public void setC_Instanciavel(boolean C_Instanciavel) {
        this.C_Instanciavel = C_Instanciavel;
    }

    public boolean isC_Instanciavel() {
        return C_Instanciavel;
    }

    public void setC_Original(boolean C_Original) {
        this.C_Original = C_Original;
    }

    public boolean isC_Original() {
        return C_Original;
    }

    public void setC_Catalogo(boolean C_Catalogo) {
        this.C_Catalogo = C_Catalogo;
    }

    public boolean isC_Catalogo() {
        return C_Catalogo;
    }

    public void setC_CodFornecedor(String C_CodFornecedor) {
        this.C_CodFornecedor = C_CodFornecedor;
    }

    public String getC_CodFornecedor() {
        return C_CodFornecedor;
    }

    public void setC_VersaoFornecedor(String C_VersaoFornecedor) {
        this.C_VersaoFornecedor = C_VersaoFornecedor;
    }

    public String getC_VersaoFornecedor() {
        return C_VersaoFornecedor;
    }

    public void setC_Versao(String C_Versao) {
        this.C_Versao = C_Versao;
    }

    public String getC_Versao() {
        return C_Versao;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setDisabled(Boolean enabeled) {
        this.disabled = enabeled;
    }

    public Boolean getDisabled() {
        return disabled;
    }


    public void setSelectedAction(int selectedAction) {
        this.selectedAction = selectedAction;
    }

    public int getSelectedAction() {
        return selectedAction;
    }

    public void setId_Curso_filled(boolean Id_Curso_filled) {
        this.Id_Curso_filled = Id_Curso_filled;
    }

    public boolean getId_Curso_filled() {
        return Id_Curso_filled;
    }

    public void setC_Name_filled(boolean C_Name_filled) {
        this.C_Name_filled = C_Name_filled;
    }

    public boolean getC_Name_filled() {
        return C_Name_filled;
    }

    public void setC_Versao_filled(boolean C_Versao_filled) {
        this.C_Versao_filled = C_Versao_filled;
    }

    public boolean getC_Versao_filled() {
        return C_Versao_filled;
    }

    public static void setEvaluation(boolean Eval) {
        GC_Helper.Evaluation = Eval;
    }

    public static boolean isEvaluation() {
        return Evaluation;
    }

    /*
    * Inicio dos metodos dos butões
    */

    public void Editar_Curso() {
        /*  this.Editar_Curso_1(); */
    }

    /**
     * Metodo que vai proporcionar a criação de um novo curso, invoca a criação de cursos do XSD, mapeado na pagina
     * Atribui um novo IDCurso e torna visivel a secção de criação do cabeçalho de um curso
     */
    public void Novo_Curso() {
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding XML_CI = bindings.getOperationBinding("CreateInsert_XMLCursos");
        OperationBinding CursoSeq = bindings.getOperationBinding("getCursoSeq");
        AttributeBinding XML_CursoID = (AttributeBinding)bindings.getControlBinding("IdCurso1");
        Number CursoID = (Number)CursoSeq.getResult();
        this.setC_Id_Curso(CursoID.toString());
        XML_CursoID.setInputValue(CursoID);
        XML_CI.execute();
        this.makeVisible();
    }

    /**
     * 1ª versão do editar Curso (dá Null pointer)
     */
    /*  private void Editar_Curso_1() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListaCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DBCursos_Rows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DBCursos_Rows.length; i++) {
            Null Pointer Aqui List_Row = ListaCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DBCursos_Rows[i].getAttribute("IdCurso"));
            ListaCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    } */

    /**
     * 2ª versão do Editar Curso (Tentar usar o CreateInsert Mapeado nas Bindings da Pagina)
     **/
    /*     private void Editar_Curso_2() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListaCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DBCursos_Rows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DBCursos_Rows.length; i++) {
            List_Row = ListaCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DBCursos_Rows[i].getAttribute("IdCurso"));
            ListaCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    } */

    /**
     * 3ª versão do Editar Curso (Tentar Usar a lista de IdCursos Mapeada na pagina)
     */

    /* private void Editar_Curso_3() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListaCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DBCursos_Rows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DBCursos_Rows.length; i++) {
            List_Row = ListaCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DBCursos_Rows[i].getAttribute("IdCurso"));
            ListaCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    } */

    /*  public void Nova_Versao() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListaCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DB_CursosRows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DB_CursosRows.length; i++) {
            List_Row = ListaCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DB_CursosRows[i].getAttribute("IdCurso"));
            ListaCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    }

    public void NC_Selecionados() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListaCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DB_CursosRows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DB_CursosRows.length; i++) {
            List_Row = ListaCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DB_CursosRows[i].getAttribute("IdCurso"));
            ListaCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
        this.makeVisible();
    }

    public void Suspender() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DB_CursosRows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DB_CursosRows.length; i++) {
            List_Row = ListCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DB_CursosRows[i].getAttribute("IdCurso"));
            ListCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    }

    public void Descontinuar() {
        BindingContainer bc = this.getBindings();
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ListCursos_Iter = bindings.findIteratorBinding("Lista_CursosIterator");
        OperationBinding action = bindings.getOperationBinding("Commit");
        Row List_Row;
        JUCtrlListBinding DBCursos_List = (JUCtrlListBinding)bc.get("GpFormacaoTestCursosView1");
        DCIteratorBinding DBCursos_Iterator = DBCursos_List.getIteratorBinding();
        Row[] DB_CursosRows = DBCursos_Iterator.getAllRowsInRange();
        for (int i = 0; i < DB_CursosRows.length; i++) {
            List_Row = ListCursos_Iter.getNavigatableRowIterator().createRow();
            List_Row.setAttribute("IdCurso", DB_CursosRows[i].getAttribute("IdCurso"));
            ListCursos_Iter.getNavigatableRowIterator().insertRow(List_Row);
        }
        action.execute();
    } */

    /**
     *Metodo auxiliar, busca todas as bindings de uma pagina.
     * @return As bindings
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Metodo auxiliar, põe a visibilidade do componente de ciração de um cabeçalho a true.
     */
    private void makeVisible() {
        this.visibility = true;
    }

    /**
     *Metodo responsavel por guardar o cabeçalho do curso na base de dados pois na pagina os dados estão a ser inseridos
     * directamente no XSD
     * @return Nada.
     */
    public String Guardar_Cursos() {
        // Add event code here...
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding commit = bindings.getOperationBinding("DBCursosCommit");
        AttributeBinding XSD_IDCurso = (AttributeBinding)bindings.getCtrlBinding("IdCurso1");
        this.setVisibility(this.isEvaluation());

        //Pegar as bindings
        BindingContainer bc = this.getBindings();
        ApplicationModuleImpl AM =
            (ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl("CursosDataControl");
        ViewObject DB_Cursos_ViewObject = AM.findViewObject("GpFormacaoTestCursosView1");

        //Gravar o novo curso na base de dados
        Row DB_Cursos_Row = DB_Cursos_ViewObject.createRow();
        if (selectedAction == 1 || selectedAction == 4) {
            //Need to inser parameters onto the row;
            /* if (this.getC_Id_Curso() != null) {
                DB_Cursos_Row.setAttribute(0, this.getC_Id_Curso());
            } */
            if (this.getC_Name() != null) {
                DB_Cursos_Row.setAttribute(1, this.getC_Name());
            }

            if (this.getC_Versao() != null) {
                DB_Cursos_Row.setAttribute(2, this.getC_Versao());
            }

            if (this.getC_Descricao() != null) {
                DB_Cursos_Row.setAttribute(3, this.getC_Descricao());
            }

            DB_Cursos_Row.setAttribute(4, this.getC_Duracao());
            DB_Cursos_Row.setAttribute(5, this.isC_Instanciavel());
            DB_Cursos_Row.setAttribute(6, this.isC_Original());
            DB_Cursos_Row.setAttribute(7, this.isC_Catalogo());
            if (this.getC_CodFornecedor() != null) {
                DB_Cursos_Row.setAttribute(8, this.getC_CodFornecedor());
            }

            if (this.getC_VersaoFornecedor() != null) {
                DB_Cursos_Row.setAttribute(9, this.getC_VersaoFornecedor());
            }
            this.reset_Data();
        }
        DB_Cursos_ViewObject.insertRow(DB_Cursos_Row);
        commit.execute();
        XSD_IDCurso.setInputValue(DB_Cursos_ViewObject.last().getAttribute("IdCurso"));
        this.makeVisible();
        this.setDisabled(Boolean.FALSE);
        return null;
    }

    /**
     * Metodo auxiliar, unica maneira que encontrei de auxiliar no depuramento, responsavel por lançar uma janela
     * com todos os dados guardados neste ficheiro do cabeçalho de um curso
     */
    private void allData() {
        PrintMessage("IdCurso: " + this.getC_Id_Curso());
        PrintMessage("Curso: " + this.getC_Name());
        PrintMessage("Versao: " + this.getC_Versao());
        PrintMessage("Descricao: " + this.getC_Descricao());
        PrintMessage("Duracao: " + this.getC_Duracao());
        PrintMessage("Instanciavel: " + this.isC_Instanciavel());
        PrintMessage("Original: " + this.isC_Original());
        PrintMessage("Catalogo: " + this.isC_Catalogo());
        PrintMessage("Cdfornecedor: " + this.getC_CodFornecedor());
        PrintMessage("Versaofornecedor: " + this.getC_VersaoFornecedor());
    }

    /**
     * Metodo que quando acopolado a um botão (por exemplo) despoleta a janela de informação
     * @param Action - O Evento que despoleta a ação
     */
    public void PrintSavedData(ActionEvent Action) {
        allData();
    }

    /**
     * Metodo de de depuração, responsavel pela criação e exibição de uma janela com uma mensagem c/ conteudo a definir
     * @param msg - A mensagem que a janela irá exibir
     */
    public void PrintMessage(String msg) {
        FacesMessage fm = new FacesMessage(msg);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    /**
     *Metodo auxiliar, acopolado á dropdown de ações
     * @param vce - Evento despoletado
     */
    public void SelectedActions(ValueChangeEvent vce) {
        // Add event code here...
        int selected_action = (Integer)vce.getNewValue();
        selectedAction = selected_action;
        switch (selected_action) {
        case 0:
            //Faz nada
            break;
        case 1:
            this.Novo_Curso();
            break;
        case 2:
            /*             this.Editar_Curso(); */
            break;
        case 3:
            /*             this.Nova_Versao(); */
            break;
        case 4:
            /*             this.NC_Selecionados(); */
            break;
        case 5:
            /*             this.Suspender(); */
            break;
        case 6:
            /*             this.Descontinuar(); */
            break;
        }
    }

    /**
     * Os seguintes tres metodos (IDCursoChanged/NomeCursoChanged/VersaoCursoChanged) controlam a visibilidade do botão
     * Submit que passa a proxima human task, apenas quando um ID, um Nome e uma Versão de um curso estiver definida
     * é que o botão Submit fica visivel.
     * 
     */

    public void IDCursoChanged(ValueChangeEvent vce) {
        // Add event code here...
        this.setId_Curso_filled(Boolean.TRUE);
        this.setC_Id_Curso(vce.getNewValue().toString());
        boolean eval = (!(this.getId_Curso_filled() && this.getC_Name_filled() && this.getC_Versao_filled()));
        this.setEvaluation(eval);
        // allData();
    }

    public void NomeCursoChanged(ValueChangeEvent vce) {
        // Add event code here...
        setC_Name(vce.getNewValue().toString());
        this.setC_Name_filled(Boolean.TRUE);
        boolean eval = (!(this.getId_Curso_filled() && this.getC_Name_filled() && this.getC_Versao_filled()));
        this.setEvaluation(eval);
        //  allData();
    }

    public void VersaoCursoChanged(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Versao(vce.getNewValue().toString());
        this.setC_Versao_filled(Boolean.TRUE);
        boolean eval = (!(this.getId_Curso_filled() && this.getC_Name_filled() && this.getC_Versao_filled()));
        this.setEvaluation(eval);
        //   allData();
    }

    /**
     * Metodo responsavel por copiar a duração para esta classe
     * @param vce
     */
    public void Duracao_VCL(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Duracao(Integer.parseInt(vce.getNewValue().toString()));
        //   allData();
    }
    
    /**
     * Metodo responsavel por copiar a descrição para esta classe
     * @param vce
     */
    public void DescricaoCursosText(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Descricao(vce.getNewValue().toString());
        //   allData();
    }
    
    /**
     * Metodo responsavel por copiar se o curso é instanciavel para esta classe
     * @param vce
     */
    public void InstanciavelCursosBoolean(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Instanciavel(Boolean.parseBoolean(vce.getNewValue().toString()));
        //   allData();
    }
    
    /**
     * Metodo responsavel por copiar se o curso é original para esta classe
     * @param vce
     */
    public void OriginalCursosBoolean(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Original(Boolean.parseBoolean(vce.getNewValue().toString()));
        //   allData();
    }
    
    /**
     * Metodo responsavel por copiar o curso é de catalogo para esta classe
     * @param vce
     */
    public void CatalogoCursosBoolean(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_Catalogo(Boolean.parseBoolean(vce.getNewValue().toString()));
        //   allData();
    }
    
    /**
     * Metodo responsavel por copiar o codigo de fornecedor para esta classe
     * @param vce
     */
    public void CodFornecedorCursosText(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_CodFornecedor(vce.getNewValue().toString());
        //  allData();
    }
    
    /**
     * Metodo responsavel por copiar a versão do para esta classe
     * @param vce
     */
    public void VersaoFornecedorCursosText(ValueChangeEvent vce) {
        // Add event code here...
        this.setC_VersaoFornecedor(vce.getNewValue().toString());
        //  allData();
    }

    /**
     * Metodo que vai resetar os dados internos a esta classe
     */
    private void reset_Data() {
        this.setC_Id_Curso(null);
        this.setC_Name(null);
        this.setC_Descricao(null);
        this.setC_Versao(null);
        this.setC_Duracao(0);
        this.setC_Instanciavel(false);
        this.setC_Original(false);
        this.setC_Catalogo(false);
        this.setC_CodFornecedor(null);
        this.setC_VersaoFornecedor(null);
    }
}
