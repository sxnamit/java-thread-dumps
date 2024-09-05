#!/bin/bash

# Input and output file paths
input_file="ABSOLUTE_INPUT_FILE_PATH"
output_file="ABSOLUTE_OUTPUT_FILE_PATH"

# Truncate the output file if it exists, or create a new empty file
> "$output_file"

# Process the file line by line
while IFS= read -r line; do
    # Check if the line contains 'nid=' followed by a hexadecimal value
    if [[ $line =~ nid=([0-9a-fA-Fx]+) ]]; then
        hex_nid="${BASH_REMATCH[1]}"
        # Convert hexadecimal nid to decimal
        dec_nid=$((hex_nid))
        # Add the decimal nid next to the hexadecimal one
        modified_line="${line/nid=$hex_nid/nid=$hex_nid dec_nid=$dec_nid}"
        echo "$modified_line" >> "$output_file"
    else
        # If no nid, print the line as is
        echo "$line" >> "$output_file"
    fi
done < "$input_file"

echo "Conversion complete. Output written to $output_file"