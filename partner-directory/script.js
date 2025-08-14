const partners = [
  { name: "Accenture", solutions: ["Cloud Migration Toolkit", "Digital Document Suite"] },
  { name: "Deloitte", solutions: ["Compliance Automation"] },
  { name: "Infosys", solutions: [] },
  { name: "PwC", solutions: ["Risk Management Platform"] }
];

const directory = document.getElementById("directory");
const filterCheckbox = document.getElementById("filterCheckbox");

function renderDirectory() {
  directory.innerHTML = "";

  const filtered = filterCheckbox.checked
    ? partners.filter(p => p.solutions.length > 0)
    : partners;

  filtered.forEach(partner => {
    const card = document.createElement("div");
    card.className = "card";

    const name = document.createElement("h2");
    name.textContent = partner.name;
    card.appendChild(name);

    partner.solutions.forEach(solution => {
      const sol = document.createElement("div");
      sol.className = "solution";
      sol.textContent = solution;
      card.appendChild(sol);
    });

    directory.appendChild(card);
  });
}

filterCheckbox.addEventListener("change", renderDirectory);
renderDirectory();
