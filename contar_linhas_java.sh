total=0
for dir in */; do
    echo "Pasta: $dir"
    linhas=$(find "$dir" -name "*.java" -type f -print0 | xargs -0 wc -l | tail -n 1)
    echo "$linhas"
    total=$((total + $(echo "$linhas" | awk '{print $1}')))
done
echo "Total geral: $total"
