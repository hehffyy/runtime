create index IDX_MATERIAL_BIZRECID on B_Material(fBizRecId);

create index IDX_MATERIAL_PARENTID on B_Material(fParentID);

create index IDX_MATERIAL_RECMAT on B_Material(fBizRecId,fMaterialId);