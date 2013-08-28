package beans;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

public class RefreshTables {
    public RefreshTables() {
    }

    /**
     * Este metodo é responsavel por refrescar a tabela das instalações de uma empresa, caso,
     * por qualquer razão os dados estejam desactualizados
     * @return null - O return é desprezado
     */
    public String ctb5_action() {
        // Add event code here...
        DCBindingContainer binding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding itb = (DCIteratorBinding)binding.get("Instalacoes_Moradas2Iterator");
        if(itb!=null){
            itb.getViewObject().clearCache();
            itb.executeQuery();
        }
        return null;
    }

/**
     * Este metodo é responsavel por refrescar a tabela das empresas de um contacto, caso,
     * por qualquer razão os dados estejam desactualizados
     * @return null - O return é desprezado
     */
    public String ctb9_action() {
        // Add event code here...
        DCBindingContainer binding = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding itb = (DCIteratorBinding)binding.get("Empresa_Contactos_P_Empresa1Iterator");
        if(itb!=null){
            itb.getViewObject().clearCache();
            itb.executeQuery();
        }
        return null;
    }
}
