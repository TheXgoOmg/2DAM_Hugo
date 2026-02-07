# -*- coding: utf-8 -*-
{
    'name': 'SistemaGestionHospitalaria',
    'version': '1.0',
    'summary': 'Sistema de Gestión Hospitalaria',
    'author': 'Hugo',
    'website': 'https://apuntesfpinformatica.es',
    'category': 'Educativo',
    'depends': ['base', 'hr'],
    'data': [
        'security/ir.model.access.csv',
        'data/ir_sequence_data.xml',
        'views/hospital_pacientes.xml',
        'views/hospital_personal.xml',
        'views/hospital_atencion_sanitaria.xml',
    ],
    'installable': True,
    'application': True,
    'license': 'AGPL-3',
}
