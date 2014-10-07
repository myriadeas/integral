CREATE VIEW CTTERM (CT05TBNAME, CT05POINTER, CT05SRAW, CT05SCONV, CT05RAW, CT05HITS, CT05COUNT, CT05AUTLINK) AS
  (    
    (SELECT 'CTINDX' AS CT05TBNAME, CT05POINTER, CT05SRAW, CT05SCONV, CT05RAW, CT05HITS, CT05COUNT, CT05AUTLINK FROM CTINDX)
    UNION ALL    
    (SELECT 'CTTITL' AS CT05TBNAME, CT05POINTER, CT05SRAW, CT05SCONV, CT05RAW, CT05HITS, CT05COUNT, CT05AUTLINK FROM CTTITL)
    
  );