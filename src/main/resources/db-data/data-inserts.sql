INSERT INTO SPL (idSPL)  VALUES 
 ('/Users/leticia/git/SPLSimple');
INSERT INTO CoreAssetBaseline (idBaseline,releaseDate,SPL_idSPL) VALUES
('baseline-v1.0','2017-09-08 15:50:45','/Users/leticia/git/SPLSimple');
INSERT INTO Feature (idFeature, name) VALUES
('FA','FA'),
('FB','FB'),
('FC','FC');
INSERT INTO CoreAssetBaseline_has_Feature (CoreAssetBaseline_idBaseline, Feature_idFeature) VALUES
('baseline-v1.0','FA'),
('baseline-v1.0','FB'),
('baseline-v1.0','FC');
INSERT INTO CoreAsset (idCoreAsset, name, path, content, size, CoreAssetBaseline_idBaseline) VALUES
(1,'Asset.java','/Users/leticia/git/SPLSimple/src/onekin/Asset.java','cGFja2FnZSBvbmVraW47CgpwdWJsaWMgY2xhc3MgQXNzZXQgewoJCglTdHJpbmcgdmFyaWFibGU7CgkKCXB1YmxpYyBBc3NldCgpewoJCXRoaXMudmFyaWFibGUgPSJpbml0aWFsaXplZCI7Cgl9CgkKCXB1YmxpYyB2b2lkIG1ldGhvZDEgKCl7CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBzZWxlY3RlZCBhbmQgbm90IEZlYXR1cmUgQiIpOwoJIAkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQycpKQoJCVN5c3RlbS5vdXQucHJpbnRsbigiRkMgc2VsZWN0ZWQuIE5lc3RlZCIpOwoJCS8vIFBWOkVORENPTkQKCS8vIFBWOkVORENPTkQKCVN5c3RlbS5vdXQucHJpbnRsbigiaW4gYmV0d2VlbiIpOwoKCS8vUFY6SUZDT05EKHB2Omhhc0ZlYXR1cmU6KCdGQicpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQyBzZWxlY3RlZC4gTmVzdGVkIik7CgkvL1BWOkVORENPTkQKCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgcHY6aGFzRmVhdHVyZSgnRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiRkEgJiBGQiBpcyBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORAoJCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBvciBwdjpoYXNGZWF0dXJlKCdGQycpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBvciBGYyBhcmUgc2VsZWN0ZWQuIik7CgkvL1BWOkVORENPTkQKCX0KfQo=',32,'baseline-v1.0');
INSERT INTO CoreAsset_has_Feature (Feature_idFeature,CoreAsset_idCoreAsset) VALUES
('FA',1),
('FB',1),
('FC',1);
INSERT INTO ProductPortfolio (idPortfolio,SPL_idSPL, CoreAssetBAseline_idBaseline) VALUES
('Series 1.X','/Users/leticia/git/SPLSimple','baseline-v1.0');
INSERT INTO Product (idProduct, name, ProductPortfolio_idPortfolio) VALUES
(1,'product1','Series 1.X');
INSERT INTO ProductRelease (idRelease, releaseDate, Product_idProduct) VALUES
('product1-v1.0','2017-09-08 15:52:42',1);
INSERT INTO ProductAsset (idProductAsset, name, path, content, size, ProductRelease_idRelease) VALUES
(1,'Asset.java','/Users/leticia/git/SPLSimple/src/onekin/Asset.java','cGFja2FnZSBvbmVraW47CgpwdWJsaWMgY2xhc3MgQXNzZXQgewoJCglTdHJpbmcgdmFyaWFibGU7CgkKCXB1YmxpYyBBc3NldCgpewoJCXRoaXMudmFyaWFibGUgPSJpbml0aWFsaXplZCI7Cgl9CgkKCXB1YmxpYyB2b2lkIG1ldGhvZDEgKCl7CgkvL1BWOklGQ09ORCAocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgKG5vdCAocHY6aGFzRmVhdHVyZSgnRkInKSkpKQoJU3lzdGVtLm91dC5wcmludGxuKCJGQSBpcyBzZWxlY3RlZCBhbmQgIEZlYXR1cmUgQiBpcyBub3QiKTsKCSAJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkMnKSkKCQlTeXN0ZW0ub3V0LnByaW50bG4oIkZDIGlzIHNlbGVjdGVkLiBJdCBpcyBOZXN0ZWQiKTsKCQkvLyBQVjpFTkRDT05ECgkvLyBQVjpFTkRDT05ECglTeXN0ZW0ub3V0LnByaW50bG4oIlRoaXMgZG9lcyBub3QgYmVsb25nIHRvIGFueSBmZWF0dXJlIik7CgoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZTooJ0ZCJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkZCIGlzIHNlbGVjdGVkIik7CgkvL1BWOkVORENPTkQKCQoJLy9QVjpJRkNPTkQocHY6aGFzRmVhdHVyZSgnRkEnKSBhbmQgcHY6aGFzRmVhdHVyZSgnRkInKSkKCVN5c3RlbS5vdXQucHJpbnRsbigiQm90aCBGQSBhbmQgRkIgYXJlIHNlbGVjdGVkLiIpOwoJLy9QVjpFTkRDT05ECgkJCgkvL1BWOklGQ09ORChwdjpoYXNGZWF0dXJlKCdGQScpIG9yIHB2Omhhc0ZlYXR1cmUoJ0ZDJykpCglTeXN0ZW0ub3V0LnByaW50bG4oIkVpdGhlciBGQSBvciBGYyBpcyBzZWxlY3RlZC4iKTsKCS8vUFY6RU5EQ09ORAoJfQp9Cg==',32,'product1-v1.0');
INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset,Feature_idFeature, isNewFeature, isNewAsset, featureNameModified) VALUES
('REMOVED',1,1,null,true,false,'null'),
('REMOVED',1,1,null,true,false,'null'),
('REMOVED',null,1,'null',false,true,'null'),
('REMOVED',1,1,null,true,false,'null'),
('REMOVED',1,1,null,true,false,'null'),
('REMOVED',1,1,null,true,false,'null'),
('ADDED',1,1,null,true,false,'null'),
('ADDED',1,1,null,true,false,'null'),
('ADDED',null,1,'null',false,true,'null'),
('ADDED',1,1,null,true,false,'null'),
('ADDED',1,1,null,true,false,'null'),
('ADDED',1,1,null,true,false,'null');
