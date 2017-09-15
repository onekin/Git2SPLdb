INSERT INTO SPL (idSPL)  VALUES 
 ('/Users/onekin/Documents/workspace/SPLSimple');
INSERT INTO CoreAssetBaseline (idBaseline,releaseDate,SPL_idSPL) VALUES
('Baseline-v1.0','2017-09-08 15:50:45','/Users/onekin/Documents/workspace/SPLSimple');
INSERT INTO Feature (idFeature, name) VALUES
('FA','FA'),
('FB','FB'),
('FC','FC');
INSERT INTO CoreAssetBaseline_has_Feature (CoreAssetBaseline_idBaseline, Feature_idFeature) VALUES
('Baseline-v1.0','FA'),
('Baseline-v1.0','FB'),
('Baseline-v1.0','FC');
INSERT INTO CoreAsset (idCoreAsset, name, path, content, size, CoreAssetBaseline_idBaseline) VALUES
(1,'Asset.java','/Users/onekin/Documents/workspace/SPLSimple/src/onekin/Asset.java','cGFja2FnZSBvbmVraW47CgpwdWJsaWMgY2xhc3MgQXNzZXQgewoJCglTdHJpbmcgdmFyaWFibGU7CgkKCXB1YmxpYyBBc3NldCgpewoJCXRoaXMudmFyaWFibGUgPSJpbml0aWFsaXplZCI7Cgl9CgkKCXB1YmxpYyB2b2lkIG1ldGhvZDEgKCl7CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBzZWxlY3RlZCBhbmQgbm90IEZlYXR1cmUgQiIpOwoJIAkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQycpKQoJCVN5c3RlbS5vdXQucHJpbnRsbigiRkMgc2VsZWN0ZWQuIE5lc3RlZCIpOwoJCS8vIFBWOkVORENPTkQKCS8vIFBWOkVORENPTkQKCVN5c3RlbS5vdXQucHJpbnRsbigiaW4gYmV0d2VlbiIpOwoKCS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmU6KCdGQicpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQyBzZWxlY3RlZC4gTmVzdGVkIik7CgkvL1BWOkVORENPTkQKCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgcHY6aGFzRmVhdHVyZSgnRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiRkEgJiBGQiBpcyBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORAoJCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBvciBwdjpoYXNGZWF0dXJlKCdGQycpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBvciBGYyBhcmUgc2VsZWN0ZWQuIik7CgkvL1BWOkVORENPTkQKCX0KfQo=',32,'Baseline-v1.0');
INSERT INTO CoreAsset_has_Feature (Feature_idFeature,CoreAsset_idCoreAsset) VALUES
('FA',1),
('FB',1),
('FC',1);
INSERT INTO ProductPortfolio (idPortfolio,SPL_idSPL, CoreAssetBAseline_idBaseline) VALUES
('Series 1.X','/Users/onekin/Documents/workspace/SPLSimple','Baseline-v1.0');
INSERT INTO Product (idProduct, name, ProductPortfolio_idPortfolio) VALUES
(1,'product1','Series 1.X');
INSERT INTO ProductRelease (idRelease, releaseDate, Product_idProduct) VALUES
('product1-v1.0','2017-09-08 15:52:42',1);
INSERT INTO ProductAsset (idProductAsset, name, path, content, size, ProductRelease_idRelease) VALUES
(1,'Asset.java','/Users/onekin/Documents/workspace/SPLSimple/src/onekin/Asset.java','cGFja2FnZSBvbmVraW47CgpwdWJsaWMgY2xhc3MgQXNzZXQgewoJCglTdHJpbmcgdmFyaWFibGU7CgkKCXB1YmxpYyBBc3NldCgpewoJCXRoaXMudmFyaWFibGUgPSJpbml0aWFsaXplZCI7Cgl9CgkKCXB1YmxpYyB2b2lkIG1ldGhvZDEgKCl7CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBpcyBzZWxlY3RlZCBhbmQgIEZlYXR1cmUgQiBpcyBub3QiKTsKCSAJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkMnKSkKCQlTeXN0ZW0ub3V0LnByaW50bG4oIkZDIGlzIHNlbGVjdGVkLiBJdCBpcyBOZXN0ZWQiKTsKCQkvLyBQVjpFTkRDT05ECgkvLyBQVjpFTkRDT05ECglTeXN0ZW0ub3V0LnByaW50bG4oIlRoaXMgZG9lcyBub3QgYmVsb25nIHRvIGFueSBmZWF0dXJlIik7CgoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZTooJ0ZCJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkZCIGlzIHNlbGVjdGVkIik7CgkvL1BWOkVORENPTkQKCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgcHY6aGFzRmVhdHVyZSgnRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiQm90aCBGQSBhbmQgRkIgYXJlIHNlbGVjdGVkLiIpOwoJLy9QVjpFTkRDT05ECgkJCgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIG9yIHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkVpdGhlciBGQSBvciBGYyBpcyBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORAoJfQp9Cg==',32,'product1-v1.0');
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(1,'CS8vUFY6SUZDT05EIChwdjpoYXNGZWF0dXJlKCdGQScpIGFuZCAobm90IChwdjpoYXNGZWF0dXJlKCdGQicpKSkp','CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBzZWxlY3RlZCBhbmQgbm90IEZlYXR1cmUgQiIpOwoJIAkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQycpKQoJCVN5c3RlbS5vdXQucHJpbnRsbigiRkMgc2VsZWN0ZWQuIE5lc3RlZCIpOwoJCS8vIFBWOkVORENPTkQKCS8vIFBWOkVORENPTkQ=',12,17,null,1,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(2,'CSAJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkMnKSk=','CgkgCS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCgkJU3lzdGVtLm91dC5wcmludGxuKCJGQyBzZWxlY3RlZC4gTmVzdGVkIik7CgkJLy8gUFY6RU5EQ09ORA==',14,16,null,1,1);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(3,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmU6KCdGQicpKQ==','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlOignRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiRkMgc2VsZWN0ZWQuIE5lc3RlZCIpOwoJLy9QVjpFTkRDT05E',20,22,null,1,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(4,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZBJykgYW5kIHB2Omhhc0ZlYXR1cmUoJ0ZCJykp','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIGFuZCBwdjpoYXNGZWF0dXJlKCdGQicpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSAmIEZCIGlzIHNlbGVjdGVkLiIpOwoJLy9QVjpFTkRDT05E',24,26,null,1,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(5,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZBJykgb3IgcHY6aGFzRmVhdHVyZSgnRkMnKSk=','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIG9yIHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkZBIG9yIEZjIGFyZSBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORA==',28,30,null,1,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(6,'CS8vUFY6SUZDT05EIChwdjpoYXNGZWF0dXJlKCdGQScpIGFuZCAobm90IChwdjpoYXNGZWF0dXJlKCdGQicpKSkp','CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBpcyBzZWxlY3RlZCBhbmQgIEZlYXR1cmUgQiBpcyBub3QiKTsKCSAJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkMnKSkKCQlTeXN0ZW0ub3V0LnByaW50bG4oIkZDIGlzIHNlbGVjdGVkLiBJdCBpcyBOZXN0ZWQiKTsKCQkvLyBQVjpFTkRDT05ECgkvLyBQVjpFTkRDT05E',12,17,1,null,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(7,'CSAJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkMnKSk=','CgkgCS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCgkJU3lzdGVtLm91dC5wcmludGxuKCJGQyBpcyBzZWxlY3RlZC4gSXQgaXMgTmVzdGVkIik7CgkJLy8gUFY6RU5EQ09ORA==',14,16,1,null,6);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(8,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmU6KCdGQicpKQ==','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlOignRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiRkIgaXMgc2VsZWN0ZWQiKTsKCS8vUFY6RU5EQ09ORA==',20,22,1,null,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(9,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZBJykgYW5kIHB2Omhhc0ZlYXR1cmUoJ0ZCJykp','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIGFuZCBwdjpoYXNGZWF0dXJlKCdGQicpKQoJU3lzdGVtLm91dC5wcmludGxuKCJCb3RoIEZBIGFuZCBGQiBhcmUgc2VsZWN0ZWQuIik7CgkvL1BWOkVORENPTkQ=',24,26,1,null,null);
INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, greatest_parent_id) VALUES
(10,'CS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmUoJ0ZBJykgb3IgcHY6aGFzRmVhdHVyZSgnRkMnKSk=','CgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIG9yIHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkVpdGhlciBGQSBvciBGYyBpcyBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORA==',28,30,1,null,null);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FA',6);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FB',6);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FC',7);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FB',8);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FA',9);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FB',9);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FA',10);
INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES
('FC',10);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,6);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,7);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,6);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,null);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,8);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,9);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('REMOVED',1,1,0,10);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,6);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,7);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,6);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,null);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,8);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,9);
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES
('ADDED',1,1,0,10);
