drop database if exists editorial;
create database editorial;
use editorial;

CREATE TABLE Revista (
    issn VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tematica VARCHAR(255),
    numero_edicion INT,
    anio_publicacion INT,
    idioma VARCHAR(50),
    periodicidad ENUM('MENSUAL','TRIMESTRAL','SEMESTRAL','ANUAL'),
    url_portada TEXT
);

CREATE TABLE Autor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE,
    afiliacion VARCHAR(255),
    pais VARCHAR(100),
    biografia TEXT
);

CREATE TABLE Articulo (
    doi VARCHAR(100) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    resumen TEXT,
    fecha_recepcion DATE,
    fecha_publicacion DATE,
    numero_paginas INT,
    idioma VARCHAR(50),
    estado ENUM('RECIBIDO','EN_REVISION','ACEPTADO','PUBLICADO','RECHAZADO'),
    issn_revista VARCHAR(20),

    FOREIGN KEY (issn_revista) REFERENCES Revista(issn)
);

CREATE TABLE Articulo_Autor (
    doi_articulo VARCHAR(100),
    id_autor BIGINT,
    orden_autoria INT,
    es_corresponsal BOOLEAN,

    PRIMARY KEY (doi_articulo, id_autor),

    FOREIGN KEY (doi_articulo) REFERENCES Articulo(doi),
    FOREIGN KEY (id_autor) REFERENCES Autor(id)
);

INSERT INTO Revista(issn, nombre, tematica, numero_edicion, anio_publicacion, idioma, periodicidad, url_portada) VALUES
('1234-5678', 'Revista de Inteligencia Artificial', 'Tecnología', 15, 2024, 'Español', 'TRIMESTRAL', 'https://ejemplo.com/portada_ia.jpg'),
('2345-6789', 'Journal of Medical Research', 'Medicina', 42, 2024, 'Inglés', 'MENSUAL', 'https://ejemplo.com/portada_medicina.jpg'),
('3456-7890', 'Revista de Educación Moderna', 'Educación', 8, 2024, 'Español', 'SEMESTRAL', 'https://ejemplo.com/portada_educacion.jpg'),
('4567-8901', 'Sustainable Development', 'Medio Ambiente', 12, 2023, 'Inglés', 'TRIMESTRAL', 'https://ejemplo.com/portada_ambiente.jpg'),
('5678-9012', 'Revista de Física Aplicada', 'Física', 25, 2024, 'Español', 'ANUAL', 'https://ejemplo.com/portada_fisica.jpg'),
('6789-0123', 'Business Innovation Journal', 'Negocios', 7, 2024, 'Inglés', 'MENSUAL', 'https://ejemplo.com/portada_negocios.jpg'),
('7890-1234', 'Revista de Psicología Contemporánea', 'Psicología', 18, 2024, 'Español', 'TRIMESTRAL', 'https://ejemplo.com/portada_psicologia.jpg'),
('8901-2345', 'Agricultural Sciences Review', 'Agricultura', 31, 2023, 'Inglés', 'SEMESTRAL', 'https://ejemplo.com/portada_agricultura.jpg');

INSERT INTO Autor (nombre_completo, correo, afiliacion, pais, biografia) VALUES
('Juan Pérez García', 'juan.perez@universidad.edu', 'Universidad Nacional', 'España', 'Doctor en Ciencias de la Computación con 15 años de experiencia en IA'),
('María López Martínez', 'maria.lopez@tecnologico.mx', 'Tecnológico de Monterrey', 'México', 'Investigadora en el área de machine learning y deep learning'),
('Carlos Rodríguez Fernández', 'carlos.rodriguez@medicalcenter.es', 'Hospital General Universitario', 'España', 'Especialista en investigación médica y ensayos clínicos'),
('Ana Sánchez Ruiz', 'ana.sanchez@educacion.edu', 'Universidad Pedagógica', 'Colombia', 'Experta en innovación educativa y tecnología educativa'),
('David Wilson Brown', 'david.wilson@environment.org', 'Environmental Research Institute', 'Canadá', 'PhD en Ciencias Ambientales, enfocado en desarrollo sostenible'),
('Laura Fernández Gómez', 'laura.fernandez@fisica.edu', 'Instituto de Física Teórica', 'Argentina', 'Investigadora en física cuántica y óptica'),
('Robert Johnson Smith', 'robert.johnson@business.edu', 'Harvard Business School', 'USA', 'Profesor de innovación y emprendimiento'),
('Carmen Ruiz González', 'carmen.ruiz@psicologia.es', 'Universidad Complutense', 'España', 'Psicóloga clínica especializada en terapia cognitivo-conductual'),
('Thomas Anderson Lee', 'thomas.lee@agriculture.org', 'FAO', 'Italia', 'Especialista en agricultura sostenible y seguridad alimentaria'),
('Patricia Martínez Castro', 'patricia.martinez@tecnologia.es', 'Universidad Politécnica', 'España', 'Ingeniera en sistemas con especialización en big data'),
('Miguel Ángel Torres', 'miguel.torres@medicina.mx', 'Instituto Nacional de Ciencias Médicas', 'México', 'Cardiólogo e investigador clínico'),
('Elena García Morales', 'elena.garcia@educacion.cl', 'Ministerio de Educación', 'Chile', 'Asesora en políticas educativas inclusivas'),
('Francisco López Jiménez', 'francisco.lopez@ambiente.ar', 'Instituto del Agua', 'Argentina', 'Investigador en recursos hídricos y cambio climático'),
('Isabella Rossi', 'isabella.rossi@fisica.it', 'Università di Bologna', 'Italia', 'Física teórica especializada en astrofísica'),
('Kevin Martínez Pérez', 'kevin.martinez@negocios.com', 'IE Business School', 'España', 'Consultor en transformación digital empresarial'),
('Natalia Ortiz Romero', 'natalia.ortiz@psicologia.mx', 'UNAM', 'México', 'Neuropsicóloga especializada en desarrollo infantil'),
('Óscar Ramírez Sánchez', 'oscar.ramirez@agricultura.es', 'Universidad de Córdoba', 'España', 'Agrónomo especializado en cultivos ecológicos'),
('Paula Mendoza Castro', 'paula.mendoza@tecnologia.co', 'Universidad de los Andes', 'Colombia', 'Ingeniera de software con enfoque en UX/UI'),
('Ricardo Herrera Díaz', 'ricardo.herrera@medicina.cl', 'Pontificia Universidad Católica', 'Chile', 'Neurólogo e investigador en enfermedades neurodegenerativas'),
('Sofia Christensen', 'sofia.christensen@ambiente.dk', 'Aarhus University', 'Dinamarca', 'Ecóloga especializada en conservación de ecosistemas');

INSERT INTO Articulo (doi, titulo, resumen, fecha_recepcion, fecha_publicacion, numero_paginas, idioma, estado, issn_revista) VALUES
('10.1234.ai001', 'Redes Neuronales para Procesamiento de Lenguaje Natural', 'Este artículo explora nuevas arquitecturas de redes neuronales para PLN', '2024-01-15', '2024-03-20', 25, 'Español', 'PUBLICADO', '1234-5678'),
('10.1234.ai002', 'Deep Learning en Visión por Computador', 'Análisis de técnicas avanzadas de deep learning para reconocimiento de imágenes', '2024-02-10', '2024-05-15', 30, 'Inglés', 'PUBLICADO', '1234-5678'),
('10.1234.ai003', 'Ética en Sistemas de IA', 'Consideraciones éticas en el desarrollo de sistemas autónomos', '2024-03-05', NULL, 18, 'Español', 'ACEPTADO', '1234-5678'),
('10.2345.med001', 'Avances en Terapia Génica para Cáncer', 'Estudio sobre nuevas terapias génicas para tratar cáncer de mama', '2023-11-20', '2024-01-10', 35, 'Inglés', 'PUBLICADO', '2345-6789'),
('10.2345.med002', 'Tratamientos Innovadores para Alzheimer', 'Evaluación de fármacos experimentales en fase III', '2024-01-08', '2024-04-01', 28, 'Inglés', 'PUBLICADO', '2345-6789'),
('10.2345.med003', 'Telemedicina Post-Pandemia', 'Análisis del impacto de la telemedicina en zonas rurales', '2024-02-20', NULL, 22, 'Español', 'EN_REVISION', '2345-6789'),
('10.3456.edu001', 'Metodologías Activas en Educación Superior', 'Implementación de aprendizaje basado en proyectos en universidades', '2024-01-25', '2024-04-10', 32, 'Español', 'PUBLICADO', '3456-7890'),
('10.3456.edu002', 'Inteligencia Artificial en el Aula', 'Uso de asistentes virtuales para personalización del aprendizaje', '2024-02-14', NULL, 26, 'Español', 'ACEPTADO', '3456-7890'),
('10.3456.edu003', 'Educación Inclusiva: Retos y Oportunidades', 'Estrategias para atender la diversidad en el aula', '2024-03-10', NULL, 20, 'Español', 'EN_REVISION', '3456-7890'),
('10.4567.amb001', 'Energías Renovables en Latinoamérica', 'Potencial y desafíos de la transición energética', '2023-10-05', '2024-01-15', 40, 'Inglés', 'PUBLICADO', '4567-8901'),
('10.4567.amb002', 'Conservación de Bosques Tropicales', 'Estrategias de protección contra la deforestación', '2023-12-12', '2024-03-25', 33, 'Inglés', 'PUBLICADO', '4567-8901'),
('10.4567.amb003', 'Impacto del Cambio Climático en Agricultura', 'Análisis de escenarios para 2030', '2024-01-18', NULL, 27, 'Español', 'RECIBIDO', '4567-8901'),
('10.5678.fis001', 'Computación Cuántica: Fundamentos', 'Introducción a qubits y puertas cuánticas', '2024-02-01', '2024-05-10', 28, 'Español', 'PUBLICADO', '5678-9012'),
('10.5678.fis002', 'Materiales Superconductores', 'Nuevos descubrimientos en superconductividad a alta temperatura', '2024-03-15', NULL, 24, 'Inglés', 'EN_REVISION', '5678-9012'),
('10.5678.fis003', 'Física de Plasmas', 'Aplicaciones en fusión nuclear controlada', '2023-12-20', '2024-04-05', 31, 'Inglés', 'PUBLICADO', '5678-9012'),
('10.6789.bus001', 'Transformación Digital en PYMES', 'Factores clave para la adopción tecnológica', '2024-01-10', '2024-03-15', 29, 'Inglés', 'PUBLICADO', '6789-0123'),
('10.6789.bus002', 'Marketing de Influencers', 'Efectividad en generación de engagement', '2024-02-05', '2024-05-01', 23, 'Español', 'PUBLICADO', '6789-0123'),
('10.6789.bus003', 'Fintech y Bancos Tradicionales', 'Competencia y colaboración en el sector financiero', '2024-03-20', NULL, 26, 'Inglés', 'ACEPTADO', '6789-0123'),
('10.7890.psi001', 'Terapia Cognitivo-Conductual Online', 'Eficacia en tratamiento de ansiedad', '2024-01-28', '2024-04-20', 34, 'Español', 'PUBLICADO', '7890-1234'),
('10.7890.psi002', 'Inteligencia Emocional en el Trabajo', 'Impacto en clima laboral y productividad', '2024-02-18', NULL, 21, 'Español', 'EN_REVISION', '7890-1234'),
('10.7890.psi003', 'Mindfulness y Reducción del Estrés', 'Estudio longitudinal en profesionales de la salud', '2024-03-01', NULL, 27, 'Inglés', 'RECIBIDO', '7890-1234'),
('10.8901.agr001', 'Agricultura de Precisión', 'Uso de drones y sensores para optimización de cultivos', '2023-11-15', '2024-02-20', 36, 'Inglés', 'PUBLICADO', '8901-2345'),
('10.8901.agr002', 'Cultivos Transgénicos: Mitos y Realidades', 'Revisión sistemática de estudios sobre seguridad alimentaria', '2024-01-05', '2024-04-15', 42, 'Español', 'PUBLICADO', '8901-2345'),
('10.8901.agr003', 'Agricultura Regenerativa', 'Prácticas para restauración de suelos degradados', '2024-02-25', NULL, 25, 'Inglés', 'ACEPTADO', '8901-2345'),
('10.1234.ai004', 'Sistemas de Recomendación', 'Algoritmos híbridos para recomendaciones personalizadas', '2024-04-01', NULL, 19, 'Inglés', 'RECIBIDO', '1234-5678'),
('10.2345.med004', 'Nanomedicina en Diagnóstico Temprano', 'Nanopartículas para detección de biomarcadores', '2024-03-12', NULL, 24, 'Inglés', 'EN_REVISION', '2345-6789'),
('10.4567.amb004', 'Economía Circular en la Industria', 'Modelos de negocio sostenibles', '2024-04-05', NULL, 22, 'Español', 'RECIBIDO', '4567-8901'),
('10.6789.bus004', 'Liderazgo en Equipos Remotos', 'Competencias directivas en entornos virtuales', '2024-03-25', NULL, 20, 'Español', 'RECIBIDO', '6789-0123');

INSERT INTO Articulo_Autor (doi_articulo, id_autor, orden_autoria, es_corresponsal) VALUES
('10.1234.ai001', 1, 1, TRUE),
('10.1234.ai001', 2, 2, FALSE),
('10.1234.ai002', 2, 1, TRUE),
('10.1234.ai002', 10, 2, FALSE),
('10.1234.ai003', 1, 1, TRUE),
('10.1234.ai003', 10, 2, FALSE),
('10.2345.med001', 3, 1, TRUE),
('10.2345.med001', 11, 2, FALSE),
('10.2345.med002', 11, 1, TRUE),
('10.2345.med002', 3, 2, FALSE),
('10.2345.med002', 19, 3, FALSE),
('10.2345.med003', 19, 1, TRUE),
('10.3456.edu001', 4, 1, TRUE),
('10.3456.edu001', 12, 2, FALSE),
('10.3456.edu002', 12, 1, TRUE),
('10.3456.edu003', 4, 1, TRUE),
('10.3456.edu003', 18, 2, FALSE),
('10.4567.amb001', 5, 1, TRUE),
('10.4567.amb001', 13, 2, FALSE),
('10.4567.amb002', 13, 1, TRUE),
('10.4567.amb002', 5, 2, FALSE),
('10.4567.amb003', 20, 1, TRUE),
('10.4567.amb003', 5, 2, FALSE),
('10.5678.fis001', 6, 1, TRUE),
('10.5678.fis001', 14, 2, FALSE),
('10.5678.fis002', 14, 1, TRUE),
('10.5678.fis002', 6, 2, FALSE),
('10.5678.fis003', 6, 1, TRUE),
('10.6789.bus001', 7, 1, TRUE),
('10.6789.bus001', 15, 2, FALSE),
('10.6789.bus002', 15, 1, TRUE),
('10.6789.bus003', 7, 1, TRUE),
('10.6789.bus003', 15, 2, FALSE),
('10.7890.psi001', 8, 1, TRUE),
('10.7890.psi001', 16, 2, FALSE),
('10.7890.psi002', 16, 1, TRUE),
('10.7890.psi002', 8, 2, FALSE),
('10.7890.psi003', 8, 1, TRUE),
('10.8901.agr001', 9, 1, TRUE),
('10.8901.agr001', 17, 2, FALSE),
('10.8901.agr002', 17, 1, TRUE),
('10.8901.agr002', 9, 2, FALSE),
('10.8901.agr003', 17, 1, TRUE),
('10.1234.ai004', 1, 1, TRUE),
('10.1234.ai004', 2, 2, FALSE),
('10.2345.med004', 3, 1, TRUE),
('10.2345.med004', 19, 2, FALSE),
('10.4567.amb004', 20, 1, TRUE),
('10.4567.amb004', 13, 2, FALSE),
('10.6789.bus004', 7, 1, TRUE),
('10.6789.bus004', 15, 2, FALSE);

use editorial;
select * from Articulo;