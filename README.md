# coconut

https://www.lirmm.fr/~bessiere/Site/Media/TER-coconut-heuristics.pdf

# Encadrant

Christian Bessiere <bessiere@lirmm.fr>

Anastasia Paparrizou <anastasia.paparrizou@lirmm.fr>

# Instances CSP
http://www.cril.univ-artois.fr/~lecoutre/#/benchmarks  
https://www.xcsp.org/instances/

# Cours
http://ktiml.mff.cuni.cz/~bartak/constraints/  
https://perso.liris.cnrs.fr/christine.solnon/Site-PPC/e-miage-ppc-som.htm

# Import

https://github.com/chocoteam/choco-parsers

# Instructions

```bash
# from the root directory of the project, unzip instances in lzma format
unzip ./instances/instancesMainXCSP22.zip

# give read / write rights
chmod +777 ./instancesMainXCSP22/CSP22/
cd ./instancesMainXCSP22/CSP22/
# give read / write rights to every individual instances
chmod +777 $(ls)
# deccompress instances from .lzma to .xml
xz --format=lzma --decompress $(ls)


cd ../../
# from the root directory of the project
# run the binaries with low level priority and redirect output to text file
nice java -jar ./target/coconut-1.0-jar-with-dependencies.jar ./instancesMainXCSP22/CSP22/ > res9.csv
```