create or replace 
PROCEDURE DELETEINSTALACAO 
(
  EID IN NUMBER  
, MID IN NUMBER  
) AS 
BEGIN
  Delete from instalacoes where empresa_id = eid and morada_id = mid;
  commit;
END DELETEINSTALACAO;
