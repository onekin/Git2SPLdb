
insert into ProductAsset (idProductAsset, name, content,size) VALUES
(1,'ProductAsset1.txt','hola\nLeti\nQue tal?\n',3),
(2,'ProductAsset2.txt','\nAicitel\\nQue tal?\\n',3),
(3,'ProductAsset3.txt','hola\\nMome\\nQue tal?\n',3);


insert into ProductAssetVP (idPV, expression, productAsset_idProductAsset) VALUES
	(1,'hasFeature("Heat"))',1)
	(2,'hasFeature("Heat"))',2)
	(3,'hasFeature("Heat"))',3)
	(4,'not (hasFeature("Temperature"))',1)


insert into productRelease (idproductRelease, name, coreassetbaseline_idcoreassetbaseline) VALUES

insert into coreassetbaseline (idCoreAssetBaseline, name) VALUES

insert into developer (iddeveloper, name, email, team_idTeam) VALUES 

insert into team (idteam, name) VALUES

insert into purpose (idpurpose,githubissue,product_idproduct) VALUES

insert into product (idProduct, name, customer_idcustomer) VALUES

insert into customization (idcustomization, newFeature, newAsset, linesAdded, linesDeleted, lineNumber, productAssetVP_idPV, 
	productrelease_idproductRelease, developer_iddeveloper, purpose_idpurpose) VALUES

