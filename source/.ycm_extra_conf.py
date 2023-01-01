import os
import ycm_core

JASL_BASE = os.environ['JASL_BASE']
JAVA_HOME = '/opt/jdk-17.0.1' #os.environ['JAVA_HOME']

flags = [
    '-x', 'c++', '-Wall', '-Wextra', '-Werror',
    '-DNDEBUG', '-DUSE_CLANG_COMPLETER', '-DYCM_EXPORT=', '-std=c++17',
    '-idirafter', '/usr/include/',
    '-isystem', JAVA_HOME + '/include/',
    '-isystem', JAVA_HOME + '/include/linux/',
    '-I', JASL_BASE + '/include/',
]

def IsHeaderFile( filename ):
    extension = os.path.splitext( filename )[ 1 ]
    return extension in [ '.h' ]

def FindCorrespondingSourceFile( filename ):
    if IsHeaderFile( filename ):
        basename = os.path.splitext( filename )[ 0 ]
        for extension in [ '.cpp', '.cxx' ]:
            replacement_file = basename + extension
            if os.path.exists( replacement_file ):
                return replacement_file
    return filename

def Settings( **kwargs ):
    if kwargs[ 'language' ] == 'cfamily':
        filename = FindCorrespondingSourceFile( kwargs[ 'filename' ] )

        return {
            'flags': flags,
            'include_paths_relative_to_dir': JASL_BASE + '/include',
            'override_filename': filename
        }

    if kwargs[ 'language' ] == 'java':
        return { 'ls': { 'java.format.onType.enabled': True } }

    if kwargs[ 'language' ] == 'python':
        return { 'sys_path': [ JASL_BASE + '/python' ] }
