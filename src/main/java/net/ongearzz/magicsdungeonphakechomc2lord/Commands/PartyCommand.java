package net.ongearzz.magicsdungeonphakechomc2lord.Commands;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Lang;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand implements CommandExecutor {
    private final Lang lang = new Lang();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] arg) {
        Player sender = (Player) commandSender;
        PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender);
        Player player = info.getPlayer();
        if (command.getName().equalsIgnoreCase("DungeonParty")) {
            if (arg.length == 0) {
                for (int i = 0; i < lang.getHelpList().size(); i++) {
                    player.sendMessage(lang.getHelpList().get(i));
                }
            } else if (arg[0].equalsIgnoreCase("invite")) {
                if (arg.length == 1) {
                    //TODO: action:
                    return true;
                } else if (arg.length == 2) {
                    String arg1 = arg[1];
                    if (info.getParty() == null) {
                        for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                            player.sendMessage(lang.getChuaCoParty().get(i));
                        }
                    } else {
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arg1))) {
                            if (Bukkit.getPlayer(arg1) != sender) {
                                if (info.getParty().isAllinv()) {
                                    if (MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender).getParty().getLeader() != sender) {
                                        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender);
                                        Player leader = info2.getParty().getLeader();
                                        PartyUtils.invite(leader, Bukkit.getPlayer(arg1), sender);
                                    }
                                } else if (info.getParty().getLeader().equals(sender)) {
                                    PartyUtils.invite(sender, Bukkit.getPlayer(arg1));
                                } else {
                                    player.sendMessage("Error");
                                }
                            } else {
                                for (int i = 0; i < lang.getA().size(); i++) {
                                    player.sendMessage(lang.getA().get(i));
                                }
                            }
                        } else {
                            for (int i = 0; i < lang.getB().size(); i++) {
                                player.sendMessage(lang.getB().get(i));
                            }                        }
                    }
                }
            } else if (arg[0].equalsIgnoreCase("create")) {
                if (arg.length == 1) {
                    try {
                        if (info.getParty() == null) {
                            PartyUtils.createParty(player, null);
                            for (int i = 0; i < lang.getPartyCreateThanhCong().size(); i++) {
                                player.sendMessage(lang.getPartyCreateThanhCong().get(i));
                            }
                        } else {
                            for (int i = 0; i < lang.getM().size(); i++) {
                                player.sendMessage(lang.getM().get(i));
                            }                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        for (int i = 0; i < lang.getPartyCreateKoThanhCong().size(); i++) {
                            player.sendMessage(lang.getPartyCreateKoThanhCong().get(i));
                        }
                    }
                }
            } else if (arg[0].equalsIgnoreCase("disband")) {
                if (arg.length == 1) {
                    if (info.getParty() != null) {
                        if (info.getParty().getLeader() == sender) {
                            PartyUtils.disband(sender);
                            for (int i = 0; i < lang.getPartyDisbandThanhCong().size(); i++) {
                                player.sendMessage(lang.getPartyDisbandThanhCong().get(i));
                            }
                        } else {
                            for (int i = 0; i < lang.getC().size(); i++) {
                                player.sendMessage(lang.getC().get(i));
                            }                          }
                    } else {
                        for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                            player.sendMessage(lang.getChuaCoParty().get(i));
                        }                      }
                }
            } else if (arg[0].equalsIgnoreCase("list")) {
                if (arg.length == 1) {
                    try {
                        if (info.getParty() != null) {
                            PartyUtils.sendList(info.getParty().getLeader(), sender);
                        } else {
                            for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                                player.sendMessage(lang.getChuaCoParty().get(i));
                            }                          }
                    } catch (Exception e) {
                        for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                            player.sendMessage(lang.getChuaCoParty().get(i));
                        }                      }
                }
            } else if (arg[0].equalsIgnoreCase("join")) {
                if (arg.length == 1) {
                    return true;
                } else if (arg.length == 2) {
                    try {
                        String party = arg[1];
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(party))) {
                            if (MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(Bukkit.getPlayer(party)).getParty() != null) {
                                PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(Bukkit.getPlayer(party));
                                if (info2.getParty().isInQueue()) {
                                    PartyUtils.addToParty(sender, Bukkit.getPlayer(party));
                                    player.sendMessage("Add Thành Công!");
                                } else if (info2.getParty().getInInvites().contains(sender)) {
                                    PartyUtils.addToParty(sender, Bukkit.getPlayer(party));
                                    player.sendMessage("Add Thành Công!");
                                } else {
                                    for (int i = 0; i < lang.getE().size(); i++) {
                                        player.sendMessage(lang.getE().get(i));
                                    }                                  }
                            }
                        }
                    } catch (Exception e) {
                        for (int i = 0; i < lang.getPartyNotFound().size(); i++) {
                            player.sendMessage(lang.getPartyNotFound().get(i));
                        }
                    }
                }
            } else if (arg[0].equalsIgnoreCase("leave")) {
                if (arg.length == 1) {
                    try {
                        if (info.getParty() != null) {
                            Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender).getParty();
                            if (sender != party2.getLeader()) {
                                PartyUtils.leave(party2, sender, false);
                            } else {
                                PartyUtils.leave(party2, sender, true);
                            }
                        } else {
                            for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                                player.sendMessage(lang.getChuaCoParty().get(i));
                            }                                  }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (arg[0].equalsIgnoreCase("kick")) {
                if (arg.length == 1) {
                    return true;
                } else if (arg.length == 2) {
                    try {
                        String target = arg[1];
                        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(target))) {
                            if (info.getParty() != null) {
                                if (info.getParty().getPlayers().contains(Bukkit.getPlayer(target).getUniqueId())) {
                                    Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender).getParty();
                                    Player leader = party2.getLeader();
                                    if (sender.equals(leader)) {
                                        if (PartyUtils.kick(party2, Bukkit.getPlayer(target))) {
                                            for (int i = 0; i < lang.getG().size(); i++) {
                                                player.sendMessage(lang.getG().get(i).replace("<player>",target));
                                            }
                                        } else {
                                            return true;
                                        }
                                    } else {
                                        for (int i = 0; i < lang.getH().size(); i++) {
                                            player.sendMessage(lang.getH().get(i));
                                        }                                              }
                                } else {
                                    for (int i = 0; i < lang.getI().size(); i++) {
                                        player.sendMessage(lang.getI().get(i));
                                    }                                          }
                            } else {
                                for (int i = 0; i < lang.getChuaCoParty().size(); i++) {
                                    player.sendMessage(lang.getChuaCoParty().get(i));
                                }                                      }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (arg[0].equalsIgnoreCase("transfer")) {
                if (arg.length == 1) {
                    return true;
                } else if (arg.length == 2) {
                    String target = arg[1];
                    try {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player) != null) {
                            Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender).getParty();
                            Player oldLeader = party2.getLeader();
                            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(target))) {
                                if (Bukkit.getPlayer(target) != oldLeader) {
                                    if (sender == oldLeader) {
                                        PartyUtils.transfer(party2, Bukkit.getPlayer(target));
                                        for (int i = 0; i < lang.getJ().size(); i++) {
                                            player.sendMessage(lang.getJ().get(i).replace("<leaderCu>",sender.getDisplayName()).replace("<leaderMoi>",target));
                                        }                                          }
                                }
                            } else {
                                for (int i = 0; i < lang.getB().size(); i++) {
                                    player.sendMessage(lang.getB().get(i));
                                }                                      }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (arg[0].equalsIgnoreCase("mute")) {
                if (arg.length == 1) {
                    try {
                        if (info.getParty() != null) {
                            Player leader = info.getParty().getLeader();
                            if (leader == sender) {
                                if (PartyUtils.mute(info.getParty())) {
                                    for (int i = 0; i < lang.getK().size(); i++) {
                                        player.sendMessage(lang.getK().get(i));
                                    }
                                } else {
                                    for (int i = 0; i < lang.getL().size(); i++) {
                                        player.sendMessage(lang.getL().get(i));
                                    }                                      }
                            } else {
                                for (int i = 0; i < lang.getH().size(); i++) {
                                    player.sendMessage(lang.getH().get(i));
                                }                                  }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (arg[0].equalsIgnoreCase("chat")) {
                if (arg.length == 1) {
                    for (int i = 0; i < lang.getchatHelp().size(); i++) {
                        player.sendMessage(lang.getchatHelp().get(i));
                    }
                    return true;
                } else {
                    if (info.getParty() != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < arg.length; i++) {
                            stringBuilder.append(arg[i]).append(" ");
                        }
                        PartyUtils.chat(info.getParty(), sender, stringBuilder.toString());
                    }
                }
            }
        }
        return true;
    }
}
//                if (plugin.getConfig().contains("PartyHelp")) {
//                        for (int i = 0; i < plugin.getConfig().getStringList("PartyHelp").size(); i++) {
//        player.sendMessage(Colored.msgColor(plugin.getConfig().getStringList("PartyHelp").get(i)));
//        }
//        }






































