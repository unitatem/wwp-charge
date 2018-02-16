#!/bin/bash -l

if [ "$#" -lt 2 ]; then
    echo "Usage: $0 <number of nodes> <number of tasks/node> [testrun|polska]"
    exit
fi

echo "Compiling.."
sh Compile.sh

if [ $? -ne 0 ]
  then exit
fi

echo "Running job on $1 nodes, $2 tasks/node.."

sbatch <<EOT
#!/bin/bash
#SBATCH --job-name  KrzemoweMozgi
#SBATCH --output run.out
#SBATCH --error  run.err
#SBATCH --nodes $1
#SBATCH --ntasks-per-node $2
#SBATCH --time 00:25:00

module load java

srun hostname > nodes.txt
#time srun -N $1 -n $1 -c $2 java -cp lib/PCJ-5.0.6.jar:lib/OSMonaut-1.1-SNAPSHOT.jar Main
EOT
