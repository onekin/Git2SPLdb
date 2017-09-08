
insert into ProductAsset (idProductAsset, name, content,size) VALUES
(1,'ProductAsset1.txt','hola\nLeti\nQue tal?\n'
	,3),
(2,'ProductAsset2.txt','\nAicitel\\nQue tal?\\n'
	,3),
(3,'ProductAsset3.txt','hola\\nMome\\nQue tal?\n'
	,3);


insert into ProductAssetVP (idPV, expression, productAsset_idProductAsset) VALUES
	(1,'hasFeature("Heat"))',1),
	(4,'not (hasFeature("Temperature"))',1),
	(2,'hasFeature("Heat"))',2),
	(3,'hasFeature("Basque"))',3);
	


insert into productRelease (idproductRelease, name, coreassetbaseline_idcoreassetbaseline) VALUES
	(1,"productNY-v1.0",1),
	(2,"productBerlin-v1.0",1),
	(3,"productDonosti-v1.0",1),
	(4,"productBilbao-v1.0",2);

insert into coreassetbaseline (idCoreAssetBaseline, name) VALUES
	(1,"Baseline-v1.0"),
	(2,"Baseline-v2.0");

insert into developer (iddeveloper, name, email, team_idTeam) VALUES 
	(1, "leti", "leti",1),
	(2, "letimome", "letimome",1);

insert into team (idteam, name) VALUES
	(1,"team1");


insert into customer (idCustomer,name) VALUES
	(1,"EuskalMet-Bilbao"),
	(2,"Met-Berlin"),
	(3,"EuskalMet-Donosti"),
	(4,"NY-Forecast");

insert into product (idProduct, name, customer_idcustomer) VALUES
	(1,"productNY",4),
	(2,"productBerlin",2),
	(3,"productBilbao",1),
	(4,"productDonosti",3);

insert into purpose (idpurpose,githubissue,product_idproduct) VALUES
	(1,"issues when showing alert of extreme heat",1),
	(2,"gale alert does not show up when temperature is enabled",2),
	(3,"initializing the heat feature not working",3),
	(4,"new language 'basque' for menues",4);



insert into customization (idcustomization, newFeature, newAsset, linesAdded, linesDeleted, lineNumber, 
	productAssetVP_idPV, productrelease_idproductRelease, developer_iddeveloper, purpose_idpurpose) VALUES
(1,0,0,1,0,24,1,1,1,1),
(2,0,0,1,0,25,1,1,1,1),
(3,0,0,1,0,26,1,1,1,1),
(4,0,0,1,0,27,1,1,1,1),
(5,0,0,1,0,28,1,1,1,1),

(6,1,0,1,0,4,3,1,1,4),
(7,1,0,1,0,5,3,1,1,4),
(8,1,0,1,0,6,3,1,1,4),
(9,1,0,1,0,7,3,1,1,4),
(10,1,0,1,0,20,3,1,1,4),
(11,1,0,1,0,21,3,1,1,4),
(12,1,0,1,0,22,3,1,1,4),

(13,1,0,1,0,4,4,1,1,4),
(14,1,0,1,0,4,4,1,1,4),
(15,1,0,1,0,4,4,1,1,4),
(16,1,0,1,0,4,4,1,1,4);


